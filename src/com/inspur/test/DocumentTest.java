package com.inspur.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.openkm.api.OKMDocument;
import com.openkm.automation.AutomationException;
import com.openkm.bean.Document;
import com.openkm.core.AccessDeniedException;
import com.openkm.core.Config;
import com.openkm.core.DatabaseException;
import com.openkm.core.FileSizeExceededException;
import com.openkm.core.ItemExistsException;
import com.openkm.core.PathNotFoundException;
import com.openkm.core.RepositoryException;
import com.openkm.core.UnsupportedMimeTypeException;
import com.openkm.core.UserQuotaExceededException;
import com.openkm.core.VirusDetectedException;
import com.openkm.extension.core.ExtensionException;

public class DocumentTest
{

	public static void main(String[] args) throws UnsupportedMimeTypeException, FileSizeExceededException, UserQuotaExceededException, VirusDetectedException, ItemExistsException, PathNotFoundException, AccessDeniedException, RepositoryException, IOException, DatabaseException, ExtensionException, AutomationException
	{
		// TODO Auto-generated method stub
		File file = new File("77654322.txt");
		InputStream inputStream = new FileInputStream(file);
		
		OKMDocument document = OKMDocument.getInstance();
		
		Document dc = new Document();
		dc.setPath("/okm:root/New folder");
		Config.HIBERNATE_SEARCH_INDEX_HOME = "E:\\OPENKM\\openkm-6.3.0-MySQL\\tomcat11\\repository\\index";
		Config.REPOSITORY_HOME = "E:\\OPENKM\\openkm-6.3.0-MySQL\\tomcat11\\repository";
		Config.RESTRICT_FILE_NAME="*~;*.bak";
		Document doc = document.create("", dc, inputStream);
	}

}
