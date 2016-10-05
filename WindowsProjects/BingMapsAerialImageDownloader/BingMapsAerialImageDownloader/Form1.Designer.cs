namespace BingMapsAerialImageDownloader
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.lat1 = new System.Windows.Forms.TextBox();
            this.lon1 = new System.Windows.Forms.TextBox();
            this.lon2 = new System.Windows.Forms.TextBox();
            this.lat2 = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.download = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 38);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(54, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Latitude 1";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 64);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(63, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Longitude 1";
            // 
            // lat1
            // 
            this.lat1.Location = new System.Drawing.Point(114, 35);
            this.lat1.Name = "lat1";
            this.lat1.Size = new System.Drawing.Size(205, 20);
            this.lat1.TabIndex = 2;
            // 
            // lon1
            // 
            this.lon1.Location = new System.Drawing.Point(114, 61);
            this.lon1.Name = "lon1";
            this.lon1.Size = new System.Drawing.Size(205, 20);
            this.lon1.TabIndex = 3;
            // 
            // lon2
            // 
            this.lon2.Location = new System.Drawing.Point(114, 156);
            this.lon2.Name = "lon2";
            this.lon2.Size = new System.Drawing.Size(205, 20);
            this.lon2.TabIndex = 4;
            // 
            // lat2
            // 
            this.lat2.Location = new System.Drawing.Point(114, 130);
            this.lat2.Name = "lat2";
            this.lat2.Size = new System.Drawing.Size(205, 20);
            this.lat2.TabIndex = 5;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 133);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(54, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Latitude 2";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(12, 159);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(63, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Longitude 2";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(13, 13);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(67, 13);
            this.label5.TabIndex = 8;
            this.label5.Text = "Coordinate 1";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(13, 107);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(67, 13);
            this.label6.TabIndex = 9;
            this.label6.Text = "Coordinate 2";
            // 
            // download
            // 
            this.download.Location = new System.Drawing.Point(13, 198);
            this.download.Name = "download";
            this.download.Size = new System.Drawing.Size(75, 23);
            this.download.TabIndex = 10;
            this.download.Text = "Download";
            this.download.UseVisualStyleBackColor = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(596, 318);
            this.Controls.Add(this.download);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.lat2);
            this.Controls.Add(this.lon2);
            this.Controls.Add(this.lon1);
            this.Controls.Add(this.lat1);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Button download;
        private System.Windows.Forms.TextBox lat2;
        private System.Windows.Forms.TextBox lon2;
        private System.Windows.Forms.TextBox lon1;
        private System.Windows.Forms.TextBox lat1;
    }
}

