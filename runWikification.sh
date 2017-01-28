#!/bin/sh
#
# The script takes a single parameter -- the ../Config filename
java -Xmx100G -cp "dist/wikifier-3.0-jar-with-dependencies.jar" edu.illinois.cs.cogcomp.wikifier.SimpleWikifier -annotateData data/testSample/sampleText/test.txt data/testSample/sampleOutput/ configs/STAND_ALONE_NO_INFERENCE.xml
