#!/bin/sh
#
# The script takes a single parameter -- the ../Config filename
java -Xmx10G -jar "dist/wikifier-3.0-jar-with-dependencies.jar" data/testSample/sampleText/test.txt data/testSample/sampleOutput/ data/linke.txt configs/STAND_ALONE_NO_INFERENCE.xml
