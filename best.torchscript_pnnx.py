import os
import numpy as np
import tempfile, zipfile
import torch
import torch.nn as nn
import torch.nn.functional as F
try:
    import torchvision
except:
    pass

class Model(nn.Module):
    def __init__(self):
        super(Model, self).__init__()

        self.model_0_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=3, kernel_size=(3,3), out_channels=32, padding=(1,1), padding_mode='zeros', stride=(2,2))
        self.model_0_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_1_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=32, kernel_size=(3,3), out_channels=64, padding=(1,1), padding_mode='zeros', stride=(2,2))
        self.model_1_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_2_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=64, kernel_size=(1,1), out_channels=32, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_2_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_3_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=64, kernel_size=(1,1), out_channels=32, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_3_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_4_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=32, kernel_size=(3,3), out_channels=32, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_4_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_5_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=32, kernel_size=(3,3), out_channels=32, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_5_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_7_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(1,1), out_channels=64, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_7_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_8_m = nn.MaxPool2d(ceil_mode=False, dilation=(1,1), kernel_size=(2,2), padding=(0,0), return_indices=False, stride=(2,2))
        self.model_9_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=64, kernel_size=(1,1), out_channels=64, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_9_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_10_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=64, kernel_size=(1,1), out_channels=64, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_10_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_11_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=64, kernel_size=(3,3), out_channels=64, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_11_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_12_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=64, kernel_size=(3,3), out_channels=64, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_12_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_14_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(1,1), out_channels=128, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_14_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_15_m = nn.MaxPool2d(ceil_mode=False, dilation=(1,1), kernel_size=(2,2), padding=(0,0), return_indices=False, stride=(2,2))
        self.model_16_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(1,1), out_channels=128, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_16_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_17_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(1,1), out_channels=128, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_17_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_18_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(3,3), out_channels=128, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_18_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_19_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(3,3), out_channels=128, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_19_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_21_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=512, kernel_size=(1,1), out_channels=256, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_21_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_22_m = nn.MaxPool2d(ceil_mode=False, dilation=(1,1), kernel_size=(2,2), padding=(0,0), return_indices=False, stride=(2,2))
        self.model_23_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(1,1), out_channels=256, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_23_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_24_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(1,1), out_channels=256, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_24_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_25_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(3,3), out_channels=256, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_25_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_26_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(3,3), out_channels=256, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_26_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_28_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=1024, kernel_size=(1,1), out_channels=512, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_28_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_29_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=512, kernel_size=(1,1), out_channels=256, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_29_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_30_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=512, kernel_size=(1,1), out_channels=256, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_30_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_31_m = nn.MaxPool2d(ceil_mode=False, dilation=(1,1), kernel_size=(5,5), padding=(2,2), return_indices=False, stride=(1,1))
        self.model_32_m = nn.MaxPool2d(ceil_mode=False, dilation=(1,1), kernel_size=(9,9), padding=(4,4), return_indices=False, stride=(1,1))
        self.model_33_m = nn.MaxPool2d(ceil_mode=False, dilation=(1,1), kernel_size=(13,13), padding=(6,6), return_indices=False, stride=(1,1))
        self.model_35_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=1024, kernel_size=(1,1), out_channels=256, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_35_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_37_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=512, kernel_size=(1,1), out_channels=256, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_37_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_38_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(1,1), out_channels=128, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_38_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_39 = nn.Upsample(mode='nearest', scale_factor=(2.000000,2.000000), size=None)
        self.model_40_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(1,1), out_channels=128, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_40_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_42_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(1,1), out_channels=64, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_42_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_43_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(1,1), out_channels=64, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_43_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_44_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=64, kernel_size=(3,3), out_channels=64, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_44_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_45_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=64, kernel_size=(3,3), out_channels=64, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_45_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_47_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(1,1), out_channels=128, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_47_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_48_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(1,1), out_channels=64, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_48_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_49 = nn.Upsample(mode='nearest', scale_factor=(2.000000,2.000000), size=None)
        self.model_50_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(1,1), out_channels=64, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_50_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_52_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(1,1), out_channels=32, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_52_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_53_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(1,1), out_channels=32, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_53_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_54_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=32, kernel_size=(3,3), out_channels=32, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_54_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_55_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=32, kernel_size=(3,3), out_channels=32, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_55_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_57_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(1,1), out_channels=64, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_57_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_58_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=64, kernel_size=(3,3), out_channels=128, padding=(1,1), padding_mode='zeros', stride=(2,2))
        self.model_58_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_60_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(1,1), out_channels=64, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_60_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_61_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(1,1), out_channels=64, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_61_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_62_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=64, kernel_size=(3,3), out_channels=64, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_62_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_63_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=64, kernel_size=(3,3), out_channels=64, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_63_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_65_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(1,1), out_channels=128, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_65_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_66_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(3,3), out_channels=256, padding=(1,1), padding_mode='zeros', stride=(2,2))
        self.model_66_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_68_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=512, kernel_size=(1,1), out_channels=128, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_68_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_69_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=512, kernel_size=(1,1), out_channels=128, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_69_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_70_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(3,3), out_channels=128, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_70_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_71_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(3,3), out_channels=128, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_71_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_73_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=512, kernel_size=(1,1), out_channels=256, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_73_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_74_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=64, kernel_size=(3,3), out_channels=128, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_74_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_75_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(3,3), out_channels=256, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_75_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_76_conv = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(3,3), out_channels=512, padding=(1,1), padding_mode='zeros', stride=(1,1))
        self.model_76_act = nn.LeakyReLU(negative_slope=0.100000)
        self.model_77_m_0 = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=128, kernel_size=(1,1), out_channels=78, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_77_m_1 = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=256, kernel_size=(1,1), out_channels=78, padding=(0,0), padding_mode='zeros', stride=(1,1))
        self.model_77_m_2 = nn.Conv2d(bias=True, dilation=(1,1), groups=1, in_channels=512, kernel_size=(1,1), out_channels=78, padding=(0,0), padding_mode='zeros', stride=(1,1))

        archive = zipfile.ZipFile('best.torchscript.pnnx.bin', 'r')
        self.model_0_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.0.conv.bias', (32), 'float32')
        self.model_0_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.0.conv.weight', (32,3,3,3), 'float32')
        self.model_1_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.1.conv.bias', (64), 'float32')
        self.model_1_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.1.conv.weight', (64,32,3,3), 'float32')
        self.model_2_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.2.conv.bias', (32), 'float32')
        self.model_2_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.2.conv.weight', (32,64,1,1), 'float32')
        self.model_3_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.3.conv.bias', (32), 'float32')
        self.model_3_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.3.conv.weight', (32,64,1,1), 'float32')
        self.model_4_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.4.conv.bias', (32), 'float32')
        self.model_4_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.4.conv.weight', (32,32,3,3), 'float32')
        self.model_5_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.5.conv.bias', (32), 'float32')
        self.model_5_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.5.conv.weight', (32,32,3,3), 'float32')
        self.model_7_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.7.conv.bias', (64), 'float32')
        self.model_7_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.7.conv.weight', (64,128,1,1), 'float32')
        self.model_9_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.9.conv.bias', (64), 'float32')
        self.model_9_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.9.conv.weight', (64,64,1,1), 'float32')
        self.model_10_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.10.conv.bias', (64), 'float32')
        self.model_10_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.10.conv.weight', (64,64,1,1), 'float32')
        self.model_11_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.11.conv.bias', (64), 'float32')
        self.model_11_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.11.conv.weight', (64,64,3,3), 'float32')
        self.model_12_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.12.conv.bias', (64), 'float32')
        self.model_12_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.12.conv.weight', (64,64,3,3), 'float32')
        self.model_14_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.14.conv.bias', (128), 'float32')
        self.model_14_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.14.conv.weight', (128,256,1,1), 'float32')
        self.model_16_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.16.conv.bias', (128), 'float32')
        self.model_16_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.16.conv.weight', (128,128,1,1), 'float32')
        self.model_17_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.17.conv.bias', (128), 'float32')
        self.model_17_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.17.conv.weight', (128,128,1,1), 'float32')
        self.model_18_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.18.conv.bias', (128), 'float32')
        self.model_18_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.18.conv.weight', (128,128,3,3), 'float32')
        self.model_19_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.19.conv.bias', (128), 'float32')
        self.model_19_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.19.conv.weight', (128,128,3,3), 'float32')
        self.model_21_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.21.conv.bias', (256), 'float32')
        self.model_21_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.21.conv.weight', (256,512,1,1), 'float32')
        self.model_23_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.23.conv.bias', (256), 'float32')
        self.model_23_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.23.conv.weight', (256,256,1,1), 'float32')
        self.model_24_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.24.conv.bias', (256), 'float32')
        self.model_24_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.24.conv.weight', (256,256,1,1), 'float32')
        self.model_25_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.25.conv.bias', (256), 'float32')
        self.model_25_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.25.conv.weight', (256,256,3,3), 'float32')
        self.model_26_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.26.conv.bias', (256), 'float32')
        self.model_26_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.26.conv.weight', (256,256,3,3), 'float32')
        self.model_28_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.28.conv.bias', (512), 'float32')
        self.model_28_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.28.conv.weight', (512,1024,1,1), 'float32')
        self.model_29_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.29.conv.bias', (256), 'float32')
        self.model_29_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.29.conv.weight', (256,512,1,1), 'float32')
        self.model_30_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.30.conv.bias', (256), 'float32')
        self.model_30_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.30.conv.weight', (256,512,1,1), 'float32')
        self.model_35_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.35.conv.bias', (256), 'float32')
        self.model_35_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.35.conv.weight', (256,1024,1,1), 'float32')
        self.model_37_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.37.conv.bias', (256), 'float32')
        self.model_37_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.37.conv.weight', (256,512,1,1), 'float32')
        self.model_38_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.38.conv.bias', (128), 'float32')
        self.model_38_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.38.conv.weight', (128,256,1,1), 'float32')
        self.model_40_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.40.conv.bias', (128), 'float32')
        self.model_40_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.40.conv.weight', (128,256,1,1), 'float32')
        self.model_42_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.42.conv.bias', (64), 'float32')
        self.model_42_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.42.conv.weight', (64,256,1,1), 'float32')
        self.model_43_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.43.conv.bias', (64), 'float32')
        self.model_43_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.43.conv.weight', (64,256,1,1), 'float32')
        self.model_44_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.44.conv.bias', (64), 'float32')
        self.model_44_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.44.conv.weight', (64,64,3,3), 'float32')
        self.model_45_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.45.conv.bias', (64), 'float32')
        self.model_45_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.45.conv.weight', (64,64,3,3), 'float32')
        self.model_47_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.47.conv.bias', (128), 'float32')
        self.model_47_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.47.conv.weight', (128,256,1,1), 'float32')
        self.model_48_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.48.conv.bias', (64), 'float32')
        self.model_48_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.48.conv.weight', (64,128,1,1), 'float32')
        self.model_50_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.50.conv.bias', (64), 'float32')
        self.model_50_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.50.conv.weight', (64,128,1,1), 'float32')
        self.model_52_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.52.conv.bias', (32), 'float32')
        self.model_52_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.52.conv.weight', (32,128,1,1), 'float32')
        self.model_53_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.53.conv.bias', (32), 'float32')
        self.model_53_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.53.conv.weight', (32,128,1,1), 'float32')
        self.model_54_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.54.conv.bias', (32), 'float32')
        self.model_54_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.54.conv.weight', (32,32,3,3), 'float32')
        self.model_55_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.55.conv.bias', (32), 'float32')
        self.model_55_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.55.conv.weight', (32,32,3,3), 'float32')
        self.model_57_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.57.conv.bias', (64), 'float32')
        self.model_57_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.57.conv.weight', (64,128,1,1), 'float32')
        self.model_58_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.58.conv.bias', (128), 'float32')
        self.model_58_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.58.conv.weight', (128,64,3,3), 'float32')
        self.model_60_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.60.conv.bias', (64), 'float32')
        self.model_60_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.60.conv.weight', (64,256,1,1), 'float32')
        self.model_61_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.61.conv.bias', (64), 'float32')
        self.model_61_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.61.conv.weight', (64,256,1,1), 'float32')
        self.model_62_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.62.conv.bias', (64), 'float32')
        self.model_62_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.62.conv.weight', (64,64,3,3), 'float32')
        self.model_63_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.63.conv.bias', (64), 'float32')
        self.model_63_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.63.conv.weight', (64,64,3,3), 'float32')
        self.model_65_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.65.conv.bias', (128), 'float32')
        self.model_65_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.65.conv.weight', (128,256,1,1), 'float32')
        self.model_66_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.66.conv.bias', (256), 'float32')
        self.model_66_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.66.conv.weight', (256,128,3,3), 'float32')
        self.model_68_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.68.conv.bias', (128), 'float32')
        self.model_68_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.68.conv.weight', (128,512,1,1), 'float32')
        self.model_69_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.69.conv.bias', (128), 'float32')
        self.model_69_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.69.conv.weight', (128,512,1,1), 'float32')
        self.model_70_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.70.conv.bias', (128), 'float32')
        self.model_70_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.70.conv.weight', (128,128,3,3), 'float32')
        self.model_71_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.71.conv.bias', (128), 'float32')
        self.model_71_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.71.conv.weight', (128,128,3,3), 'float32')
        self.model_73_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.73.conv.bias', (256), 'float32')
        self.model_73_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.73.conv.weight', (256,512,1,1), 'float32')
        self.model_74_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.74.conv.bias', (128), 'float32')
        self.model_74_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.74.conv.weight', (128,64,3,3), 'float32')
        self.model_75_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.75.conv.bias', (256), 'float32')
        self.model_75_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.75.conv.weight', (256,128,3,3), 'float32')
        self.model_76_conv.bias = self.load_pnnx_bin_as_parameter(archive, 'model.76.conv.bias', (512), 'float32')
        self.model_76_conv.weight = self.load_pnnx_bin_as_parameter(archive, 'model.76.conv.weight', (512,256,3,3), 'float32')
        self.model_77_m_0.bias = self.load_pnnx_bin_as_parameter(archive, 'model.77.m.0.bias', (78), 'float32')
        self.model_77_m_0.weight = self.load_pnnx_bin_as_parameter(archive, 'model.77.m.0.weight', (78,128,1,1), 'float32')
        self.model_77_m_1.bias = self.load_pnnx_bin_as_parameter(archive, 'model.77.m.1.bias', (78), 'float32')
        self.model_77_m_1.weight = self.load_pnnx_bin_as_parameter(archive, 'model.77.m.1.weight', (78,256,1,1), 'float32')
        self.model_77_m_2.bias = self.load_pnnx_bin_as_parameter(archive, 'model.77.m.2.bias', (78), 'float32')
        self.model_77_m_2.weight = self.load_pnnx_bin_as_parameter(archive, 'model.77.m.2.weight', (78,512,1,1), 'float32')
        archive.close()

    def load_pnnx_bin_as_parameter(self, archive, key, shape, dtype, requires_grad=True):
        return nn.Parameter(self.load_pnnx_bin_as_tensor(archive, key, shape, dtype), requires_grad)

    def load_pnnx_bin_as_tensor(self, archive, key, shape, dtype):
        _, tmppath = tempfile.mkstemp()
        tmpf = open(tmppath, 'wb')
        with archive.open(key) as keyfile:
            tmpf.write(keyfile.read())
        tmpf.close()
        m = np.memmap(tmppath, dtype=dtype, mode='r', shape=shape).copy()
        os.remove(tmppath)
        return torch.from_numpy(m)

    def forward(self, v_0):
        v_1 = self.model_0_conv(v_0)
        v_2 = self.model_0_act(v_1)
        v_3 = self.model_1_conv(v_2)
        v_4 = self.model_1_act(v_3)
        v_5 = self.model_2_conv(v_4)
        v_6 = self.model_2_act(v_5)
        v_7 = self.model_3_conv(v_4)
        v_8 = self.model_3_act(v_7)
        v_9 = self.model_4_conv(v_8)
        v_10 = self.model_4_act(v_9)
        v_11 = self.model_5_conv(v_10)
        v_12 = self.model_5_act(v_11)
        v_13 = torch.cat((v_12, v_10, v_8, v_6), dim=1)
        v_14 = self.model_7_conv(v_13)
        v_15 = self.model_7_act(v_14)
        v_16 = self.model_8_m(v_15)
        v_17 = self.model_9_conv(v_16)
        v_18 = self.model_9_act(v_17)
        v_19 = self.model_10_conv(v_16)
        v_20 = self.model_10_act(v_19)
        v_21 = self.model_11_conv(v_20)
        v_22 = self.model_11_act(v_21)
        v_23 = self.model_12_conv(v_22)
        v_24 = self.model_12_act(v_23)
        v_25 = torch.cat((v_24, v_22, v_20, v_18), dim=1)
        v_26 = self.model_14_conv(v_25)
        v_27 = self.model_14_act(v_26)
        v_28 = self.model_15_m(v_27)
        v_29 = self.model_16_conv(v_28)
        v_30 = self.model_16_act(v_29)
        v_31 = self.model_17_conv(v_28)
        v_32 = self.model_17_act(v_31)
        v_33 = self.model_18_conv(v_32)
        v_34 = self.model_18_act(v_33)
        v_35 = self.model_19_conv(v_34)
        v_36 = self.model_19_act(v_35)
        v_37 = torch.cat((v_36, v_34, v_32, v_30), dim=1)
        v_38 = self.model_21_conv(v_37)
        v_39 = self.model_21_act(v_38)
        v_40 = self.model_22_m(v_39)
        v_41 = self.model_23_conv(v_40)
        v_42 = self.model_23_act(v_41)
        v_43 = self.model_24_conv(v_40)
        v_44 = self.model_24_act(v_43)
        v_45 = self.model_25_conv(v_44)
        v_46 = self.model_25_act(v_45)
        v_47 = self.model_26_conv(v_46)
        v_48 = self.model_26_act(v_47)
        v_49 = torch.cat((v_48, v_46, v_44, v_42), dim=1)
        v_50 = self.model_28_conv(v_49)
        v_51 = self.model_28_act(v_50)
        v_52 = self.model_29_conv(v_51)
        v_53 = self.model_29_act(v_52)
        v_54 = self.model_30_conv(v_51)
        v_55 = self.model_30_act(v_54)
        v_56 = self.model_31_m(v_55)
        v_57 = self.model_32_m(v_55)
        v_58 = self.model_33_m(v_55)
        v_59 = torch.cat((v_58, v_57, v_56, v_55), dim=1)
        v_60 = self.model_35_conv(v_59)
        v_61 = self.model_35_act(v_60)
        v_62 = torch.cat((v_61, v_53), dim=1)
        v_63 = self.model_37_conv(v_62)
        v_64 = self.model_37_act(v_63)
        v_65 = self.model_38_conv(v_64)
        v_66 = self.model_38_act(v_65)
        v_67 = self.model_39(v_66)
        v_68 = self.model_40_conv(v_39)
        v_69 = self.model_40_act(v_68)
        v_70 = torch.cat((v_69, v_67), dim=1)
        v_71 = self.model_42_conv(v_70)
        v_72 = self.model_42_act(v_71)
        v_73 = self.model_43_conv(v_70)
        v_74 = self.model_43_act(v_73)
        v_75 = self.model_44_conv(v_74)
        v_76 = self.model_44_act(v_75)
        v_77 = self.model_45_conv(v_76)
        v_78 = self.model_45_act(v_77)
        v_79 = torch.cat((v_78, v_76, v_74, v_72), dim=1)
        v_80 = self.model_47_conv(v_79)
        v_81 = self.model_47_act(v_80)
        v_82 = self.model_48_conv(v_81)
        v_83 = self.model_48_act(v_82)
        v_84 = self.model_49(v_83)
        v_85 = self.model_50_conv(v_27)
        v_86 = self.model_50_act(v_85)
        v_87 = torch.cat((v_86, v_84), dim=1)
        v_88 = self.model_52_conv(v_87)
        v_89 = self.model_52_act(v_88)
        v_90 = self.model_53_conv(v_87)
        v_91 = self.model_53_act(v_90)
        v_92 = self.model_54_conv(v_91)
        v_93 = self.model_54_act(v_92)
        v_94 = self.model_55_conv(v_93)
        v_95 = self.model_55_act(v_94)
        v_96 = torch.cat((v_95, v_93, v_91, v_89), dim=1)
        v_97 = self.model_57_conv(v_96)
        v_98 = self.model_57_act(v_97)
        v_99 = self.model_58_conv(v_98)
        v_100 = self.model_58_act(v_99)
        v_101 = torch.cat((v_100, v_81), dim=1)
        v_102 = self.model_60_conv(v_101)
        v_103 = self.model_60_act(v_102)
        v_104 = self.model_61_conv(v_101)
        v_105 = self.model_61_act(v_104)
        v_106 = self.model_62_conv(v_105)
        v_107 = self.model_62_act(v_106)
        v_108 = self.model_63_conv(v_107)
        v_109 = self.model_63_act(v_108)
        v_110 = torch.cat((v_109, v_107, v_105, v_103), dim=1)
        v_111 = self.model_65_conv(v_110)
        v_112 = self.model_65_act(v_111)
        v_113 = self.model_66_conv(v_112)
        v_114 = self.model_66_act(v_113)
        v_115 = torch.cat((v_114, v_64), dim=1)
        v_116 = self.model_68_conv(v_115)
        v_117 = self.model_68_act(v_116)
        v_118 = self.model_69_conv(v_115)
        v_119 = self.model_69_act(v_118)
        v_120 = self.model_70_conv(v_119)
        v_121 = self.model_70_act(v_120)
        v_122 = self.model_71_conv(v_121)
        v_123 = self.model_71_act(v_122)
        v_124 = torch.cat((v_123, v_121, v_119, v_117), dim=1)
        v_125 = self.model_73_conv(v_124)
        v_126 = self.model_73_act(v_125)
        v_127 = self.model_74_conv(v_98)
        v_128 = self.model_74_act(v_127)
        v_129 = self.model_75_conv(v_112)
        v_130 = self.model_75_act(v_129)
        v_131 = self.model_76_conv(v_126)
        v_132 = self.model_76_act(v_131)
        v_133 = self.model_77_m_0(v_128)
        v_134 = v_133.view(1, 3, 26, 80, 80)
        v_135 = self.model_77_m_1(v_130)
        v_136 = v_135.view(1, 3, 26, 40, 40)
        v_137 = self.model_77_m_2(v_132)
        v_138 = v_137.view(1, 3, 26, 20, 20)
        v_139 = torch.permute(input=v_138, dims=(0,1,3,4,2))
        v_140 = v_139.contiguous(memory_format=torch.contiguous_format)
        v_141 = torch.permute(input=v_136, dims=(0,1,3,4,2))
        v_142 = v_141.contiguous(memory_format=torch.contiguous_format)
        v_143 = torch.permute(input=v_134, dims=(0,1,3,4,2))
        v_144 = v_143.contiguous(memory_format=torch.contiguous_format)
        v_145 = [v_144, v_142, v_140]
        return v_145

def export_torchscript():
    net = Model()
    net.eval()

    torch.manual_seed(0)
    v_0 = torch.rand(1, 3, 640, 640, dtype=torch.float)

    mod = torch.jit.trace(net, v_0)
    mod.save("best.torchscript_pnnx.py.pt")

def export_onnx():
    net = Model()
    net.eval()

    torch.manual_seed(0)
    v_0 = torch.rand(1, 3, 640, 640, dtype=torch.float)

    torch.onnx._export(net, v_0, "best.torchscript_pnnx.py.onnx", export_params=True, operator_export_type=torch.onnx.OperatorExportTypes.ONNX_ATEN_FALLBACK, opset_version=13, input_names=['in0'], output_names=['out0'])

def test_inference():
    net = Model()
    net.eval()

    torch.manual_seed(0)
    v_0 = torch.rand(1, 3, 640, 640, dtype=torch.float)

    return net(v_0)
