package com.inspur.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import com.openkm.api.OKMDocument;
import com.openkm.api.OKMMail;
import com.openkm.api.OKMRepository;
import com.openkm.bean.Document;
import com.openkm.bean.Mail;
import com.openkm.core.DatabaseException;
import com.openkm.core.MimeTypeConfig;
import com.openkm.core.PathNotFoundException;
import com.openkm.core.RepositoryException;
import com.openkm.frontend.client.OKMException;
import com.openkm.frontend.client.bean.GWTDocument;
import com.openkm.frontend.client.constants.service.ErrorCode;
import com.openkm.servlet.frontend.DownloadServlet;
import com.openkm.util.FileUtils;
import com.openkm.util.MailUtils;
import com.openkm.util.PathUtils;
import com.openkm.util.WebUtils;

/**
 * Servlet implementation class DownloadFileTest
 */
@WebServlet("*.DownloadFile")
public class DownloadFileTest extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String fileName;
	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadFileTest()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		System.out.println("***********DOGET***********");
		HttpSession session = request.getSession();
		fileName = request.getParameter("fileName");
		GWTDocument doc = (GWTDocument) session.getAttribute(fileName);
		System.out.println("******" + doc.getName());
		// request.setAttribute("uuid", "e084a7ef-f174-4917-8fac-afd667e63d25");

		// Download Method
		this.downloadFile(request, response,doc.getUuid());

	}

	private void downloadFile(HttpServletRequest request,
			HttpServletResponse response,String uuid) throws ServletException, IOException
	{
		DownloadServlet download = new DownloadServlet();
		
		request.setCharacterEncoding("UTF-8");
		String path = request.getParameter("path");
		//String uuid = "e084a7ef-f174-4917-8fac-afd667e63d25";
		String checkout = request.getParameter("checkout");
		String ver = request.getParameter("ver");
		boolean inline = request.getParameter("inline") != null;
		File tmp = File.createTempFile("okm", ".tmp");
		InputStream is = null;
		download.updateSessionManager(request);

		try
		{
			// Now an document can be located by UUID
			if (uuid != null && !uuid.equals(""))
			{
				path = OKMRepository.getInstance().getNodePath(null, uuid);
			} else if (path != null)
			{
				path = new String(path.getBytes("ISO-8859-1"), "UTF-8");
			}
			
			
			if (OKMDocument.getInstance().isValid(null, path))
			{
				// Get document
				Document doc = OKMDocument.getInstance().getProperties(null,
						path);

				if (ver != null && !ver.equals(""))
				{
					is = OKMDocument.getInstance().getContentByVersion(null,
							path, ver);
				} else
				{
					is = OKMDocument.getInstance().getContent(null, path,
							checkout != null);
				}

				// Send document
				String fileName = PathUtils.getName(doc.getPath());
				WebUtils.sendFile(request, response, fileName,
						doc.getMimeType(), inline, is);
			} else if (OKMMail.getInstance().isValid(null, path))
			{
				// Get mail
				Mail mail = OKMMail.getInstance().getProperties(null, path);

				// Send mail
				ServletOutputStream sos = response.getOutputStream();
				String fileName = PathUtils.getName(mail.getSubject() + ".eml");
				WebUtils.prepareSendFile(request, response, fileName,
						MimeTypeConfig.MIME_EML, inline);
				response.setContentLength((int) mail.getSize());
				MimeMessage m = MailUtils.create(null, mail);
				m.writeTo(sos);
				sos.flush();
				sos.close();
			}

		} catch (PathNotFoundException e)
		{
			throw new ServletException(new OKMException(ErrorCode.get(
					ErrorCode.ORIGIN_OKMDownloadService,
					ErrorCode.CAUSE_PathNotFound), e.getMessage()));
		} catch (RepositoryException e)
		{
			throw new ServletException(new OKMException(ErrorCode.get(
					ErrorCode.ORIGIN_OKMDownloadService,
					ErrorCode.CAUSE_Repository), e.getMessage()));
		} catch (IOException e)
		{
			throw new ServletException(new OKMException(ErrorCode.get(
					ErrorCode.ORIGIN_OKMDownloadService, ErrorCode.CAUSE_IO),
					e.getMessage()));
		} catch (DatabaseException e)
		{
			throw new ServletException(new OKMException(ErrorCode.get(
					ErrorCode.ORIGIN_OKMDownloadService,
					ErrorCode.CAUSE_Database), e.getMessage()));
		} catch (Exception e)
		{
			throw new ServletException(new OKMException(ErrorCode.get(
					ErrorCode.ORIGIN_OKMDownloadService,
					ErrorCode.CAUSE_General), e.getMessage()));
		} finally
		{
			IOUtils.closeQuietly(is);
			FileUtils.deleteQuietly(tmp);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		System.out.println("***********DOPOST***********");
	}

}
