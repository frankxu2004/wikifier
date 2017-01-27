package edu.illinois.cs.cogcomp.wikifier.utils.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;
import org.apache.lucene.util.Version;

/**
 * Converts Unicode characters to the corresponding ASCII characters
 * as well as deliminating texts on dashes and underscores
 * @author cheng88
 *
 */
public class ASCIIEnglishAnalyzer extends StopwordAnalyzerBase{

	public ASCIIEnglishAnalyzer(Version version) {
		super(version);
	}

    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
    	final Tokenizer source = new StandardTokenizer(matchVersion, reader);
    	TokenStream result = new StandardFilter(matchVersion, source);
    	result = new ASCIIFoldingFilter(result);
    	result = new EnglishPossessiveFilter(matchVersion, result);
    	result = new WordDelimiterFilter(result,WordDelimiterFilter.ALPHA,null);
    	result = new LowerCaseFilter(matchVersion, result);
    	result = new StopFilter(matchVersion, result, EnglishAnalyzer.getDefaultStopSet());
    	result = new PorterStemFilter(result);
    	return new TokenStreamComponents(source, result);
    }
	
}