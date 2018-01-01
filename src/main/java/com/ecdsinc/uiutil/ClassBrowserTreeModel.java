package com.ecdsinc.uiutil;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.jar.*;
import java.util.zip.*;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;

import com.ecdsinc.util.Util;

public class ClassBrowserTreeModel implements TreeModel {

	private ClassBrowserTreeNode        rootNode;
	private String                      libDirPrefix;
	private String                      pathSeparator;
	private String                      fileSeparator;


	public ClassBrowserTreeModel(String classpath) {

		this.libDirPrefix = null;
		this.rootNode = new ClassBrowserRootTreeNode("Classes", null);
		this.pathSeparator = System.getProperty("path.separator");
		this.fileSeparator = System.getProperty("file.separator");

		buildTree(classpath);
    }


    public Object getRoot() {

		return this.rootNode;

    }


	public Object getChild(Object parent, int index) {

		return ((ClassBrowserTreeNode) parent).getChild(index);
    }


    public int getChildCount(Object parent) {

		return ((ClassBrowserTreeNode) parent).getNumChildren();
    }


	public boolean isLeaf(Object node) {

		return ((ClassBrowserTreeNode) node).isLeaf();
    }


    public int getIndexOfChild(Object parent, Object child) {

		return ((ClassBrowserTreeNode) parent).getIndexOfChild((ClassBrowserTreeNode) child);
    }


	public void valueForPathChanged(TreePath path, Object newValue) {

		//  Does nothing, the user can not modify the tree
    }


    public void addTreeModelListener(TreeModelListener l) {

		//  This model does not fire any events to this method is ignored
    }


    public void removeTreeModelListener(TreeModelListener l) {

		//  This model does not fire any events to this method is ignored
    }


	public void addLibrary(String libPath) {

		//  Try to open library as a jar file
		JarFile     libJar = null;

		try {
		    libJar = new JarFile(libPath);
			addLibraryJar(libJar);
			return;
		}
		catch (IOException except) {
		}

		//  libPath did not specify a jar file.  See if it is a directory
		File        libDir = null;

		libDir = new File(libPath);
		if (libDir.isDirectory()) {

			this.libDirPrefix = libDir.getAbsolutePath();
			addLibraryDir(libDir);
			this.libDirPrefix = null;
			return;
		}

		//  library did not specify a jar file or a directory.  Should an
		//  exception be thrown here?

	}


	public void addLibraryJar (JarFile libJar) {

		Enumeration<JarEntry>    entries = libJar.entries();
		ZipEntry        curEntry = null;

		while (entries.hasMoreElements()) {

			curEntry = (ZipEntry) entries.nextElement();
			if (isClass(curEntry.getName())) {

				addClass(curEntry.getName());
			}
		}
	}


	public void addLibraryDir (File libDir) {

		File[]          files = libDir.listFiles();
		File            curFile = null;
		String          className;

		for (int index = 0; index < files.length; index++) {

			curFile = files[index];
			if (curFile.isDirectory()) {

				addLibraryDir(curFile);
			}
			else if (isClass(curFile.getName())) {

				if (this.libDirPrefix == null) {

					className = curFile.getAbsolutePath();
				}
				else
				{
					className = curFile.getAbsolutePath().substring(libDirPrefix.length()+1);
				}

				addClass(className);
			}
		}
	}

	private void buildTree(String classpath) {


		StringTokenizer     libraries = new StringTokenizer(classpath,
													        this.pathSeparator);

		while (libraries.hasMoreTokens()) {

			addLibrary(libraries.nextToken());
		}
	}

	private boolean isClass(String name) {

		boolean     result = false;

		int extIndex = name.lastIndexOf(".");
		if (extIndex > 0) {

			String ext = name.substring(extIndex+1);
			if (ext.equalsIgnoreCase("class")) {

				result = true;
			}
		}
		return result;
	}

	private void addClass(String fullClassName) {

		String      packageName;
		String      className;
		int         index;
		String      separator = this.fileSeparator;

		index = fullClassName.lastIndexOf(separator);
		if (index <= 0) {

			//  Jar files always use the forward slash as a file separator.
			//  Try again using the forward slash
			separator = "/";
			index = fullClassName.lastIndexOf(separator);
			if (index <= 0) {

				//  Not a valid class specification.  Ignore it
	    		return;
			}
		}

		className = Util.stripExtension(fullClassName.substring(index+1));
		packageName = fullClassName.substring(0, index);

		StringTokenizer         tokens = new StringTokenizer(packageName,
									    				     separator);

		String                  curToken = null;
		ClassBrowserTreeNode    curNode = this.rootNode;
		ClassBrowserTreeNode    nextNode = null;

		while (tokens.hasMoreTokens()) {

		    curToken = tokens.nextToken();
			nextNode = curNode.getChild(curToken);

			if (nextNode == null) {

				nextNode = curNode.addChild(curToken, "");
			}

			curNode = nextNode;
		}

		//  curNode is the lowest level node of the package.  Add the class to
		//  this node.  Make sure it doesn't already exist first.
		nextNode = curNode.getChild(className);
		if (nextNode == null) {

			curNode.addChild(className,
				Util.convertPathnameToClassName(Util.stripExtension(fullClassName)));
		}
	}
}