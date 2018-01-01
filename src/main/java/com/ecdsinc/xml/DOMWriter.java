package com.ecdsinc.xml;

import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class DOMWriter {

	DOMSource       source = null;
	StreamResult    result = null;
	Transformer     transformer = null;
	boolean         localOutputStream;

    public DOMWriter(String url) {

		this.result = new StreamResult(url);
		localOutputStream = true;
    }

	public DOMWriter(OutputStream outStream) {

		this.result = new StreamResult(outStream);
		localOutputStream = false;
	}

	public DOMWriter(File file) {

		this.result = new StreamResult(file);
		localOutputStream = true;
	}

	public void write(Node node) throws XMLException {

		if (this.result == null) {

			throw new XMLException("DOMWriter is closed");
		}

		// Use a Transformer for output
		try {

			if (this.transformer == null) {

				TransformerFactory factory = TransformerFactory.newInstance();
				this.transformer = factory.newTransformer();
		        this.source = new DOMSource();
			}

			this.source.setNode(node);
			this.transformer.transform(this.source, this.result);
		}
		catch (TransformerConfigurationException except) {

			throw new XMLException("Could not locate a suitable Transformer: " + except);
		}
		catch (TransformerException except) {

			throw new XMLException("Error writing node: " + except);
		}
		finally {
		}

	}
	public void close() {

		if (localOutputStream) {

			OutputStream    outStream = this.result.getOutputStream();
	    	if (outStream != null) {

		    	try {
			    	outStream.close();
		    	}
			    catch (IOException except) {
			    }
	    	}
		}

		this.result = null;
		this.transformer = null;
	}
}