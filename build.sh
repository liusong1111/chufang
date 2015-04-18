#!/bin/bash
CROSSWALK_PATH=/Users/sliu/Downloads/crosswalk-11.40.277.7

#CROSSWALK_PATH=/Users/sliu/Downloads/crosswalk-13.41.313.0
python $CROSSWALK_PATH/make_apk.py --mode=embedded --package=com.lianliankan --manifest=resources/public/manifest.json --arch=arm --enable-remote-debugging


