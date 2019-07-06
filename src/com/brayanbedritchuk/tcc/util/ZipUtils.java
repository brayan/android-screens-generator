package com.brayanbedritchuk.tcc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

	public static void compactarArquivos(String path) throws IOException {
		List<String> arquivos = new ArrayList<>();
		gerarListaArquivos(path, new File(path), arquivos);
		compactar(path, arquivos);
	}

	public static List<String> gerarListaArquivos(String path, File node, List<String> arquivos) {

		if (node.isFile()) {
			String nomeArquivo = formatarNomeArquivo(path, node.getAbsoluteFile().toString());
			arquivos.add(nomeArquivo);
		}

		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				gerarListaArquivos(path, new File(node, filename), arquivos);
			}
		}

		return arquivos;
	}

	private static String formatarNomeArquivo(String pathSource, String file) {
		return file.substring(pathSource.length(), file.length());
	}

	private static void compactar(String sourceFolder, List<String> listaArquivos)
			throws IOException {

		byte[] buffer = new byte[1024];

		FileOutputStream fileOutput = new FileOutputStream(sourceFolder + ".zip");
		ZipOutputStream zipOutput = new ZipOutputStream(fileOutput);

		for (String arquivo : listaArquivos) {

			ZipEntry zipEntry = new ZipEntry(arquivo);
			zipOutput.putNextEntry(zipEntry);

			String path = sourceFolder + File.separator + arquivo;
			FileInputStream fileInput = new FileInputStream(path);

			int tamanho;
			while ((tamanho = fileInput.read(buffer)) > 0) {
				zipOutput.write(buffer, 0, tamanho);
			}

			fileInput.close();
		}

		zipOutput.closeEntry();
		zipOutput.close();
		fileOutput.close();
	}
}
