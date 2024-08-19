package com.example.ProiectMagazinSuplimenteAlimentare.exporter;

/**
 * FileExporter interface is used to export data from the server to the client.

 */
public class TXTFileExporter implements FileExporter{
    @Override
    public String exportData(Object object) {
        return object.toString();
    }
}