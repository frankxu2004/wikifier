package edu.illinois.cs.cogcomp.wikifier;

import edu.illinois.cs.cogcomp.edison.sentences.TextAnnotation;
import edu.illinois.cs.cogcomp.wikifier.common.GlobalParameters;
import edu.illinois.cs.cogcomp.wikifier.inference.InferenceEngine;
import edu.illinois.cs.cogcomp.wikifier.models.LinkingProblem;
import edu.illinois.cs.cogcomp.wikifier.models.ReferenceInstance;
import edu.illinois.cs.cogcomp.wikifier.utils.io.InFile;
import edu.illinois.cs.cogcomp.wikifier.utils.io.OutFile;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SimpleWikifier {

	public static void main(String[] args) throws Exception{
		if (args.length <= 2)
            return;
		GlobalParameters.loadConfig(args[args.length-1]);

		String inPath = args[0];
		String outPath = args[1];
		String entityListPath = args[2];
		File f = new File(inPath);
		List<String> texts = new ArrayList<>();
        String[] lines = InFile.readFileText(inPath).split("\\r?\\n");
//        StringBuilder temp = new StringBuilder();
//        for (int i =0; i < lines.length; i++){
//            temp.append(lines[i])
//                    .append("\n");
//            if ((i != 0 && i%100 == 0) || i == lines.length-1){
//                texts.add(temp.toString());
//                temp.setLength(0);
//            }
//        }
        texts.addAll(Arrays.asList(lines));
        HashSet<String> knownEntities = loadKnownEntities(entityListPath);

		InferenceEngine inference = new InferenceEngine(false);
        OutFile out = new OutFile(outPath + f.getName()+".el");
		for (int i = 0; i < texts.size(); i++)
			if(!StringUtils.containsOnly(texts.get(i), " \n\r")) {
                try {
                    //System.out.println("Processing No.: " + i);
                    TextAnnotation ta = GlobalParameters.curator.getTextAnnotation(texts.get(i));
                    LinkingProblem problem = new LinkingProblem(Integer.toString(i), ta, new ArrayList<ReferenceInstance>());
                    inference.annotate(problem, null, false, false, 0);
                    String wikificationString = problem.simpleWikificationString(knownEntities);
                    out.println(wikificationString);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
		    }
        out.close();
	}

	public static HashSet<String> loadKnownEntities(String filename) throws IOException {
	    HashSet<String> knownEntities = new HashSet<>();
        Collections.addAll(knownEntities, InFile.readFileText(filename).toLowerCase().split("\\r?\\n"));
        return knownEntities;
    }
}
