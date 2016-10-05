using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Media.Imaging;
using System.IO;
using System.Net;

using System.Diagnostics;

namespace BingMapsBoundigBoxImage
{
    public partial class MainWindow : Window
    {
        private string bingMapsKey = "AiFsHxaCXicSV0u8AHW-yNz68-EoHKhCR0-onkYttLcAelv5IRzjb6Uf90YLT0Ag";

        private const double maxImageWidth = 900;
        private const double maxImageHeight = 834;

        private const double EarthRadius = 6378137;
        private const double MinLatitude = -85.05112878;
        private const double MaxLatitude = 85.05112878;
        private const double MinLongitude = -180;
        private const double MaxLongitude = 180;

        public MainWindow()
        {
            InitializeComponent();
        }

        private void GetImageBtn_Clicked(object sender, RoutedEventArgs e)
        {
            double north, south, east, west;

            if(!double.TryParse(NorthTbx.Text, out north) || north > MaxLatitude || north < MinLatitude)
            {
                MessageBox.Show("North value is invalid.");
                return;
            }

            if (!double.TryParse(SouthTbx.Text, out south) || south > north || south > MaxLatitude || south < MinLatitude)
            {
                MessageBox.Show("South value is invalid.");
                return;
            }

            if (!double.TryParse(EastTbx.Text, out east) || east > MaxLongitude || east < MinLongitude)
            {
                MessageBox.Show("East value is invalid.");
                return;
            }

            if (!double.TryParse(WestTbx.Text, out west) || west > MaxLongitude || west < MinLongitude)
            {
                MessageBox.Show("West value is invalid.");
                return;
            }

            double latitude = north - south;
            double longitude = east - west;

            int zoom = 19; 


            Point topLeftCorner = LatLongToPixelXY(north, west, zoom);
            Point bottomRightCorner = LatLongToPixelXY(south, east, zoom);

            int mapWidth = (int)(bottomRightCorner.X - topLeftCorner.X);
            int mapHeight = (int)(bottomRightCorner.Y - topLeftCorner.Y);

            int col = (int)(mapWidth / maxImageWidth + 1);
            int row = (int)(mapHeight / maxImageHeight + 1);

            double[,] imgLatitude = new double[row, col];
            double[,] imgLongitude = new double[row, col];

            int colWidth = mapWidth / col;
            int rowHeight = mapHeight / row;

            Debug.WriteLine("colWidth {0}, rowHeight {1}, col {2}, row {3}", colWidth, rowHeight, col, row);

            byte[] Canvas = new byte[mapHeight * mapWidth * 4];

            string[,] imgUrlArray = new string[row, col];

            byte[] imageBytes = null;

            for (int i = 0; i < row; i++)
            {
                for (int j = 0; j < col; j++)
                {
                    imgLatitude[i, j] = north - latitude / col * (i + 0.5);
                    imgLongitude[i, j] = west + longitude / row * (j + 0.5);
                    imgUrlArray[i, j] = string.Format("http://dev.virtualearth.net//REST/v1/Imagery/Map/Aerial/{0},{1}/{2}?mapSize={3},{4}&key={5}", imgLatitude[i, j], imgLongitude[i, j], zoom, colWidth, rowHeight, bingMapsKey);
                    using (var webClient = new WebClient())
                    {
                        imageBytes = webClient.DownloadData(imgUrlArray[i, j]);
                    }
                    Debug.WriteLine("loop i {0}, j {1}", i, j);
                    //Canvas = PutOnCanvas(Canvas, imageBytes, colWidth * j, rowHeight * i, rowHeight, colWidth, mapWidth);
                }
            }

            //OutputTbx.Text = string.Format("Center: {0},{1}\r\nZoom Level: {2}\r\nMap Dimensions: {3},{4}", latitude, longitude, zoom, mapWidth, mapHeight);

            //Create a URL to the Bing Maps REST Imagery Service.
            //string imgUrl = string.Format("http://dev.virtualearth.net//REST/v1/Imagery/Map/Aerial/{0},{1}/{2}?mapSize={3},{4}&key={5}", latitude, longitude, zoom, mapWidth, mapHeight, bingMapsKey);
            
            //SavePhoto(imgUrl);

            //MapImg.Source = LoadImage(Canvas);
            MapImg.Source = new BitmapImage(new Uri(imgUrlArray[0, 0], UriKind.Absolute)); //LoadImage(imageBytes); //new BitmapImage(new Uri(imgUrl, UriKind.Absolute));

        }


        private static Point LatLongToPixelXY(double latitude, double longitude, int levelOfDetail)
        {
            latitude = Clip(latitude, MinLatitude, MaxLatitude);
            longitude = Clip(longitude, MinLongitude, MaxLongitude);

            double x = (longitude + 180) / 360;
            double sinLatitude = Math.Sin(latitude * Math.PI / 180);
            double y = 0.5 - Math.Log((1 + sinLatitude) / (1 - sinLatitude)) / (4 * Math.PI);

            uint mapSize = MapSize(levelOfDetail);
            int pixelX = (int)Clip(x * mapSize + 0.5, 0, mapSize - 1);
            int pixelY = (int)Clip(y * mapSize + 0.5, 0, mapSize - 1);

            return new Point(pixelX, pixelY);
        }


        private static double Clip(double n, double minValue, double maxValue)
        {
            return Math.Min(Math.Max(n, minValue), maxValue);
        }


        private static uint MapSize(int levelOfDetail)
        {
            return (uint)256 << levelOfDetail;
        }

        private void SavePhoto(string istrImagePath)
        {
            BitmapImage objImage = new BitmapImage(new Uri(istrImagePath, UriKind.Absolute));

            objImage.DownloadCompleted += objImage_DownloadCompleted;
        }

        private void objImage_DownloadCompleted(object sender, EventArgs e)
        {
            JpegBitmapEncoder encoder = new JpegBitmapEncoder();
            Guid photoID = System.Guid.NewGuid();
            String photolocation = "N " + NorthTbx.Text + " W " + WestTbx.Text + " S " + SouthTbx.Text + " E " + EastTbx.Text + ".jpg";  //file name 

            encoder.Frames.Add(BitmapFrame.Create((BitmapImage)sender));

            using (var filestream = new FileStream(photolocation, FileMode.Create))
                encoder.Save(filestream);
        }

        private static byte[] PutOnCanvas(byte[] Canvas, byte[] Image, int x, int y, int imageheight, int imagewidth, int CanvasWidth)
        {
            int k = 0;
            for (int row = y; row < y + imageheight; row++)
            {
                for (int col = x; col < x + imagewidth; col++)
                {
                    for (int i = 0; i < 4; i++)
                    {
                        k++;
                        Debug.WriteLine("Canvas x {0}, y {1}, k {2}", x, y, k);
                        Canvas[(row * CanvasWidth + col) * 4 + i] = Image[((row - y) * imagewidth + (col - x)) * 4 + i];
                    }
                }
            }
            return Canvas;
        }

        private static BitmapImage LoadImage(byte[] imageData)
        {
            if (imageData == null || imageData.Length == 0) return null;
            var image = new BitmapImage();
            using (var mem = new MemoryStream(imageData))
            {
                mem.Position = 0;
                image.BeginInit();
                image.CreateOptions = BitmapCreateOptions.PreservePixelFormat;
                image.CacheOption = BitmapCacheOption.OnLoad;
                image.UriSource = null;
                image.StreamSource = mem;
                image.EndInit();
            }
            image.Freeze();
            return image;
        }
    }
}
