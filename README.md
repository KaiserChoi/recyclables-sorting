#The model: yolov7-main 
## train p5 models
python train.py --workers 8 --device 0 --batch-size 32 --data data/coco.yaml --img 640 640 --cfg cfg/training/yolov7.yaml --weights '' --name yolov7 --hyp data/hyp.scratch.p5.yaml

##The improvement code: DCNv3(models/opt_dcnv3)
DCN requirement
pip install -U openmim
mim install mmcv-full==1.5.0
pip install timm==0.6.11 mmdet==2.28.1
pip install opencv-python termcolor yacs pyyaml scipy

windows:
python setup.py build install
linux:
sh make.sh

##Reference:
https://github.com/WongKinYiu/yolov7
https://github.com/OpenGVLab/InternImage


#The server: server
spring framework


#The client: application
##pnnx-20221116-windows
make the .pt file  -> .ncnn

##opencv-mobile-4.6.0-android
The client dependencies
