import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.util.*;
    
public class separat0r {

    static class MyFile implements Comparable<MyFile> {
	public long size;
	public String name;
	public MyFile (long size, String name) {
	    this.size = size;
	    this.name = name;
	};
	public int compareTo(MyFile o) {
	    return new Long(this.size).compareTo(new Long(o.size));
	}
    }

    static Stack<MyFile> makeBucket (long size, TreeSet<MyFile> files) {
	Stack<MyFile> bucket = new Stack<MyFile>();

	for (Iterator<MyFile> it = files.descendingIterator();
	     it.hasNext();) {
	    MyFile m = it.next();
	    if (size >= m.size) {
		size -= m.size;
		bucket.push(m);
		it.remove();
	    }
	}

	return bucket;
    }

    public static Stack<Stack<MyFile> > calculateBuckets
	(long bucketsize, TreeSet<MyFile> files) {
	Stack<Stack<MyFile> > ret = new Stack<Stack<MyFile> >();
	Stack<MyFile> c;
	while (!((c = makeBucket(bucketsize, files)).isEmpty())) ret.push(c);
	return ret;
    }

    static MyFile preparePath (String path) {
	File n = new File(path);
	long l = n.length();
	if (l == 0L) {
	    System.err.println("The file '" + path + "' has length 0. This is not allowed.");
	    System.exit(-1);
	}
	return new MyFile(l, path);
    }

    public static void main (String args[]) {
	long bucketsize = Long.parseLong(args[0]);
	
	TreeSet<MyFile> philes = new TreeSet<MyFile>();
	for (int i = 1; i < args.length; ++i) {
	    philes.add(preparePath(args[i]));
	}

	if (philes.last().size > bucketsize) {
	    System.err.println("The file '" + philes.last().name + "' is too large for the buckets.");
	    System.exit(-1);
	}

	Stack<Stack<MyFile> > buckets =
	    calculateBuckets (bucketsize, philes);

	System.out.println("<buckets>");

	for (Stack<MyFile> st : buckets) {
	    System.out.println("\t<bucket>");
	    for (MyFile mf : st) {
		// TODO: xmlescape (?)
		System.out.println("\t\t<file><![CDATA[" + mf.name.replace("]]>", "]]]]><![CDATA[>") + "]]></file>");
	    }
	    System.out.println("\t</bucket>");
	}
    	System.out.println("</buckets>");
    }
}
