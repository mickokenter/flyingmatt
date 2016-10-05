start_pic = 393408726;
round = 10;
temp = 20;
J=cell(1,round);
for j=1:round
    I=cell(1,temp);
    for i=1:temp     
        path=strcat(int2str(start_pic+i-1+(j-1)*temp),'.jpg');
        t=imread(path);
        t1=rgb2gray(t);
        t2=adapthisteq(t1);
        t3=im2bw(t2,0.75);
        I{i}=t3;  
    end  
    result=I{1};
    for i=2:temp
        x=size(I{i},1);
        y=size(I{i},2);
        for a = 1:x
            for b = 1:y
                result(a,b) = or(result(a,b),I{i}(a,b));
            end
        end
    end
    J{j}=result;
    %figure;
    %imshow(result);
end
combination=J{1};
for j = 2:round
    p=size(J{j},1);
    q=size(J{j},2);
    for a = 1:p
        for b = 1:q
            combination(a,b)=or(combination(a,b),J{j}(a,b));
        end
    end
end
figure;
imshow(combination);