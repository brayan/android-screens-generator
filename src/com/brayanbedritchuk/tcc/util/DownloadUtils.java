package com.brayanbedritchuk.tcc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.brayanbedritchuk.tcc.model.TelaAndroid;

public class DownloadUtils {

	private static final String MIME_TYPE = "application/octet-stream";
	private static final String HEADER_KEY = "Content-Disposition";

	public static void efetuarDownload(ServletContext context, TelaAndroid tela,
			HttpServletResponse response) throws IOException {

		limparBuffer(response);
		iniciarDownload(context, tela, response);
		apagarArquivosGerados(tela);
	}

	private static void iniciarDownload(ServletContext context, TelaAndroid tela,
			HttpServletResponse response) throws FileNotFoundException, IOException {
		String path = tela.getPath() + ".zip";
		File downloadFile = new File(path);
		FileInputStream inStream = new FileInputStream(downloadFile);

		String mimeType = context.getMimeType(path);
		if (mimeType == null) {
			mimeType = MIME_TYPE;
		}
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(HEADER_KEY, headerValue);

		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		while ((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inStream.close();
		outStream.close();
	}

	private static void limparBuffer(HttpServletResponse response) {
		response.reset();
	}

	private static void apagarArquivosGerados(TelaAndroid tela) throws IOException {
		File arquivoZip = new File(tela.getPath() + ".zip");
		arquivoZip.delete();
		File diretorioGerado = new File(tela.getPath());
		FileUtils.deleteDirectory(diretorioGerado);
	}
}
