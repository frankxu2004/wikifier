package edu.illinois.cs.cogcomp.wikifier.utils.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.lang3.StringUtils;

/**
 * Parses comma separated strings to list of strings
 * @author cheng88
 *
 */
public class CSVAdapter extends XmlAdapter<String,List<String>>{

    @Override
    public List<String> unmarshal(String v) throws Exception {
        return new ArrayList<>(Arrays.asList(StringUtils.split(v,',')));
    }

    @Override
    public String marshal(List<String> v) throws Exception {
        return StringUtils.join(v,',');
    }
    
    public static class CSVSetAdapter extends XmlAdapter<String,Set<String>>{

        @Override
        public Set<String> unmarshal(String v) throws Exception {
            return new HashSet<>(Arrays.asList(StringUtils.split(v,',')));
        }

        @Override
        public String marshal(Set<String> v) throws Exception {
            return StringUtils.join(v,',');
        }
        
    }

}