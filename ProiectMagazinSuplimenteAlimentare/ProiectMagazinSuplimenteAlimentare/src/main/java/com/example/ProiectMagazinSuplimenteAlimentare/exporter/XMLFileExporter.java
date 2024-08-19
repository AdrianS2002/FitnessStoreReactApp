package com.example.ProiectMagazinSuplimenteAlimentare.exporter;

import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import com.example.ProiectMagazinSuplimenteAlimentare.wrapper.ProductsWrapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.List;

public class XMLFileExporter implements FileExporter {

    @Override
    public String exportData(Object object) {
        if (!(object instanceof List<?>)) {
            throw new IllegalArgumentException("This exporter requires a list of products.");
        }
        try {
            JAXBContext context = JAXBContext.newInstance(ProductsWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            ProductsWrapper wrapper = new ProductsWrapper();
            wrapper.setProducts((List<Product>) object);
            marshaller.marshal(wrapper, writer);

            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}