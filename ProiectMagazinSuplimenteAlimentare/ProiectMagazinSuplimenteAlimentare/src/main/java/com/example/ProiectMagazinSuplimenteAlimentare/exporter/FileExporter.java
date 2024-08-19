package com.example.ProiectMagazinSuplimenteAlimentare.exporter;

/**
 * FileExporter interface is used to export data from the server to the client.
 */
public interface FileExporter {
    String exportData(Object object);
}
