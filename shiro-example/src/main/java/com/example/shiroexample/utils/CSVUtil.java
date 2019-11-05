package com.example.shiroexample.utils;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author micuncang
 * @date 2019/6/29
 */
public class CSVUtil {

    public static final char TDF_SEPARATOR = '\t';
    private static final Logger logger = LoggerFactory.getLogger(CSVUtil.class);

    private static String[] generateClazzColumnMapping(Class clazz) {
//        BeanInfo beanInfo = null;
//        try {
//            beanInfo = Introspector.getBeanInfo(clazz);
//            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
//                System.out.println(propertyDescriptor.getName());
//            }
//            return Arrays.stream(propertyDescriptors).filter(p -> p.getPropertyType() != Class.class).map(p -> p.getName()).toArray(String[]::new);
//        } catch (IntrospectionException e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
        Field fields[] = clazz.getDeclaredFields();
        return Arrays.stream(fields).filter(f->!"serialVersionUID".equals(f.getName()))
                .map
                (f->f.getName()).toArray(String[]::new);
//        for (Field field : fields) {
//            System.out.println(field.getName());
//        }
//        return null;
    }

    public static void bean2csv(String path, List data, Class clazz) {
        try {
            //文件防重名
            path = generateUniquePath(path);
            File file = new File(path);
            if (file.exists()) {
                file.createNewFile();
            }
            Writer writer = Files.newBufferedWriter(Paths.get(path));
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(clazz);
            mappingStrategy.setColumnMapping(generateClazzColumnMapping(clazz));
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).withMappingStrategy(mappingStrategy)
                    .withSeparator(TDF_SEPARATOR)
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(data);
            writer.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static String generateUniquePath(String path) {
        File file = new File(path);
        for (int i = 0; file.exists(); i++) {
            if (i > 100) {
                logger.error("file name suffix >100", new Exception());
                break;
            }
            String[] split = path.split(".");
            path = split[0] + "_" + i;
            if (split.length > 1) {
                StringBuilder pathBuilder = new StringBuilder(path);
                for (int j = 1; j < split.length; j++) {
                    pathBuilder.append(split[j]);
                }
                path = pathBuilder.toString();
            }
            file = new File(path);
        }
        return path;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> csv2bean(Reader reader, Class clazz, boolean withHeader, char delimiter) {
        ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
        mappingStrategy.setType(clazz);
        mappingStrategy.setColumnMapping(generateClazzColumnMapping(clazz));
        CsvToBeanBuilder csvToBeanBuilder = null;
        csvToBeanBuilder = new CsvToBeanBuilder(reader).withMappingStrategy(mappingStrategy).withSeparator(delimiter)
                .withQuoteChar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES);
        if (withHeader) {
            csvToBeanBuilder.withSkipLines(1);
        }
        CsvToBean csvToBean = csvToBeanBuilder.build();
        return csvToBean.parse();

    }

}
