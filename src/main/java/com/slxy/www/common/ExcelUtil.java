package com.slxy.www.common;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Jason on 2017/2/20.
 */
public class ExcelUtil {

    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName, int sheetSize, OutputStream out) throws Exception {
        if(list != null && list.size() != 0) {
            if(sheetSize > '\uffff' || sheetSize < 1) {
                sheetSize = '\uffff';
            }

            try {
                WritableWorkbook wwb = Workbook.createWorkbook(out);
                double e = Math.ceil((double)list.size() / (new Integer(sheetSize)).doubleValue());

                for(int i = 0; (double)i < e; ++i) {
                    WritableSheet sheet;
                    if(1.0D == e) {
                        sheet = wwb.createSheet(sheetName, i);
                        fillSheet(sheet, list, fieldMap, 0, list.size() - 1);
                    } else {
                        sheet = wwb.createSheet(sheetName + (i + 1), i);
                        int firstIndex = i * sheetSize;
                        int lastIndex = (i + 1) * sheetSize - 1 > list.size() - 1?list.size() - 1:(i + 1) * sheetSize - 1;
                        fillSheet(sheet, list, fieldMap, firstIndex, lastIndex);
                    }
                }

                wwb.write();
                wwb.close();
            } catch (Exception var12) {
                var12.printStackTrace();
                if(var12 instanceof Exception) {
                    throw (Exception)var12;
                } else {
                    throw new Exception("导出Excel失败");
                }
            }
        } else {
            throw new Exception("数据源中没有任何数据");
        }
    }

    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String collectionName, String title, String content, String sheetName, int sheetSize, OutputStream out) throws Exception {
        if(list != null && list.size() != 0) {
            if(sheetSize > '\uffff' || sheetSize < 1) {
                sheetSize = '\uffff';
            }

            try {
                WritableWorkbook wwb = Workbook.createWorkbook(out);
                double e = Math.ceil((double)list.size() / (new Integer(sheetSize)).doubleValue());

                for(int i = 0; (double)i < e; ++i) {
                    WritableSheet sheet;
                    if(1.0D == e) {
                        sheet = wwb.createSheet(sheetName, i);
                        fillSheet(sheet, list, fieldMap, collectionName, title, content, 0, list.size() - 1);
                    } else {
                        sheet = wwb.createSheet(sheetName + (i + 1), i);
                        int firstIndex = i * sheetSize;
                        int lastIndex = (i + 1) * sheetSize - 1 > list.size() - 1?list.size() - 1:(i + 1) * sheetSize - 1;
                        fillSheet(sheet, list, fieldMap, collectionName, title, content, firstIndex, lastIndex);
                    }
                }

                wwb.write();
                wwb.close();
            } catch (Exception var15) {
                var15.printStackTrace();
                if(var15 instanceof Exception) {
                    throw (Exception)var15;
                } else {
                    throw new Exception("导出Excel失败");
                }
            }
        } else {
            throw new Exception("数据源中没有任何数据");
        }
    }

    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName, OutputStream out) throws Exception {
        listToExcel(list, fieldMap, sheetName, '\uffff', (OutputStream)out);
    }

    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName, int sheetSize, HttpServletResponse response, String fileName) throws Exception {
        if(fileName == null || fileName.trim().equals("")) {
            fileName = (new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date()).toString();
        }

        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");

        try {
            ServletOutputStream e = response.getOutputStream();
            listToExcel(list, fieldMap, sheetName, sheetSize, (OutputStream)e);
        } catch (Exception var7) {
            var7.printStackTrace();
            if(var7 instanceof Exception) {
                throw (Exception)var7;
            } else {
                throw new Exception("导出Excel失败");
            }
        }
    }

    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String collectionName, String title, String content, String sheetName, int sheetSize, HttpServletResponse response, String fileName) throws Exception {
        if(fileName == null || fileName.trim().equals("")) {
            fileName = (new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date()).toString();
        }

        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");

        try {
            ServletOutputStream e = response.getOutputStream();
            listToExcel(list, fieldMap, collectionName, title, content, sheetName, sheetSize, e);
        } catch (Exception var10) {
            var10.printStackTrace();
            if(var10 instanceof Exception) {
                throw (Exception)var10;
            } else {
                throw new Exception("导出Excel失败");
            }
        }
    }

    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName, int sheetSize, HttpServletResponse response) throws Exception {
        String fileName = (new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date()).toString();
        listToExcel(list, fieldMap, sheetName, sheetSize, response, fileName);
    }

    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String collectionName, String title, String content, String sheetName, HttpServletResponse response) throws Exception {
        listToExcel(list, fieldMap, collectionName, title, content, sheetName, '\uffff', response, "");
    }

    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName, HttpServletResponse response) throws Exception {
        listToExcel(list, fieldMap, sheetName, '\uffff', (HttpServletResponse)response);
    }

    public static <T> void leadToExcelQuestionBankTemplet(LinkedHashMap<String, String> fieldMap, String sheetName, HttpServletResponse response) throws Exception {
        String fileName = (new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date()).toString();
        if(fileName == null || fileName.trim().equals("")) {
            fileName = (new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date()).toString();
        }

        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");

        try {
            ServletOutputStream e = response.getOutputStream();
            char sheetSize = '\uffff';

            try {
                WritableWorkbook wwb = Workbook.createWorkbook(e);
                double e1 = 1.0D;
                if(1.0D == e1) {
                    WritableSheet sheet = wwb.createSheet(sheetName, 1);
                    String[] enFields = new String[fieldMap.size()];
                    String[] cnFields = new String[fieldMap.size()];
                    int count = 0;

                    for(Iterator i = fieldMap.entrySet().iterator(); i.hasNext(); ++count) {
                        Map.Entry label = (Map.Entry)i.next();
                        enFields[count] = (String)label.getKey();
                        cnFields[count] = (String)label.getValue();
                    }

                    for(int var17 = 0; var17 < cnFields.length; ++var17) {
                        Label var18 = new Label(var17, 0, cnFields[var17]);
                        sheet.addCell(var18);
                    }

                    setColumnAutoSize(sheet, 5);
                }

                wwb.write();
                wwb.close();
            } catch (Exception var15) {
                var15.printStackTrace();
                if(var15 instanceof Exception) {
                    throw (Exception)var15;
                } else {
                    throw new Exception("导出Excel失败");
                }
            }
        } catch (Exception var16) {
            var16.printStackTrace();
            if(var16 instanceof Exception) {
                throw (Exception)var16;
            } else {
                throw new Exception("导出Excel失败");
            }
        }
    }

    public static <T> List<T> excelToList(InputStream in, String sheetName, Class<T> entityClass, LinkedHashMap<String, String> fieldMap, String[] uniqueFields) throws Exception {
        ArrayList resultList = new ArrayList();

        try {
            Workbook e = Workbook.getWorkbook(in);
            Sheet sheet = e.getSheet(sheetName);
            int realRows = 0;

            int isExist;
            for(int firstRow = 0; firstRow < sheet.getRows(); ++firstRow) {
                int excelFieldNames = 0;

                for(isExist = 0; isExist < sheet.getColumns(); ++isExist) {
                    Cell excelFieldList = sheet.getCell(isExist, firstRow);
                    if(excelFieldList == null || "".equals(excelFieldList.getContents().toString())) {
                        ++excelFieldNames;
                    }
                }

                if(excelFieldNames == sheet.getColumns()) {
                    break;
                }

                ++realRows;
            }

            if(realRows <= 1) {
                throw new Exception("Excel文件中没有任何数据或存在空行");
            } else {
                Cell[] var24 = sheet.getRow(0);
                String[] var25 = new String[var24.length];

                for(isExist = 0; isExist < var24.length; ++isExist) {
                    var25[isExist] = var24[isExist].getContents().toString().trim();
                }

                boolean var26 = true;
                List var27 = Arrays.asList(var25);
                Iterator colMap = fieldMap.keySet().iterator();

                while(colMap.hasNext()) {
                    String uniqueCells = (String)colMap.next();
                    if(!var27.contains(uniqueCells)) {
                        var26 = false;
                        break;
                    }
                }

                if(!var26) {
                    throw new Exception("Excel中缺少必要的字段，或字段名称有误");
                } else {
                    LinkedHashMap var28 = new LinkedHashMap();

                    for(int var29 = 0; var29 < var25.length; ++var29) {
                        var28.put(var25[var29], Integer.valueOf(var24[var29].getColumn()));
                    }

                    Cell[][] var30 = new Cell[uniqueFields.length][];

                    int i;
                    int entity;
                    for(i = 0; i < uniqueFields.length; ++i) {
                        entity = ((Integer)var28.get(uniqueFields[i])).intValue();
                        var30[i] = sheet.getColumn(entity);
                    }

                    for(i = 1; i < realRows; ++i) {
                        entity = 0;

                        for(int j = 0; j < uniqueFields.length; ++j) {
                            String entry = var30[j][i].getContents();
                            Cell cnNormalName = sheet.findCell(entry, var30[j][i].getColumn(), var30[j][i].getRow() + 1, var30[j][i].getColumn(), var30[j][realRows - 1].getRow(), true);
                            if(cnNormalName != null) {
                                ++entity;
                            }
                        }

                        if(entity == uniqueFields.length && uniqueFields.length != 0) {
                            throw new Exception("Excel中有重复行，请检查");
                        }
                    }

                    for(i = 1; i < realRows; ++i) {
                        Object var31 = entityClass.newInstance();
                        Iterator var32 = fieldMap.entrySet().iterator();

                        while(var32.hasNext()) {
                            Map.Entry var33 = (Map.Entry)var32.next();
                            String var34 = (String)var33.getKey();
                            String enNormalName = (String)var33.getValue();
                            int col = ((Integer)var28.get(var34)).intValue();
                            String content = sheet.getCell(col, i).getContents().toString().trim();
                            if(!StringUtils.equals(content, "无")) {
                                setFieldValueByName(enNormalName, content, var31);
                            }
                        }

                        resultList.add(var31);
                    }

                    return resultList;
                }
            }
        } catch (Exception var23) {
            var23.printStackTrace();
            if(var23 instanceof Exception) {
                throw (Exception)var23;
            } else {
                var23.printStackTrace();
                throw new Exception("导入Excel失败");
            }
        }
    }

    public static Object getFieldValueByName(String fieldName, Object o) throws Exception {
        Object value = null;
        Field field = getFieldByName(fieldName, o.getClass());
        if(field != null) {
            field.setAccessible(true);
            value = field.get(o);
            return value;
        } else {
            throw new Exception(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
        }
    }

    public static Field getFieldByName(String fieldName, Class<?> clazz) {
        Field[] selfFields = clazz.getDeclaredFields();
        Field[] superClazz = selfFields;
        int var4 = selfFields.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Field field = superClazz[var5];
            if(field.getName().equals(fieldName)) {
                return field;
            }
        }

        Class var7 = clazz.getSuperclass();
        if(var7 != null && var7 != Object.class) {
            return getFieldByName(fieldName, var7);
        } else {
            return null;
        }
    }

    public static List getFieldByClass(Class<?> clazz) {
        ArrayList list = new ArrayList();
        Field[] selfFields = clazz.getDeclaredFields();
        Field[] superClazz = selfFields;
        int superFields = selfFields.length;

        for(int var5 = 0; var5 < superFields; ++var5) {
            Field field = superClazz[var5];
            list.add(field.getName());
        }

        Class var9 = clazz.getSuperclass();
        Field[] var10 = var9.getDeclaredFields();
        Field[] var11 = var10;
        int var12 = var10.length;

        for(int var7 = 0; var7 < var12; ++var7) {
            Field field1 = var11[var7];
            list.add(field1.getName());
        }

        return list;
    }

    public static List getSuperClassFieldByClass(Class<?> clazz) {
        ArrayList list = new ArrayList();
        Class superClazz = clazz.getSuperclass();
        Field[] superFields = superClazz.getDeclaredFields();
        Field[] var4 = superFields;
        int var5 = superFields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            list.add(field.getName());
        }

        return list;
    }

    public static Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception {
        Object value = null;
        String[] attributes = fieldNameSequence.split("\\.");
        if(attributes.length == 1) {
            value = getFieldValueByName(fieldNameSequence, o);
        } else {
            Object fieldObj = getFieldValueByName(attributes[0], o);
            String subFieldNameSequence = fieldNameSequence.substring(fieldNameSequence.indexOf(".") + 1);
            value = getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
        }

        return value;
    }

    public static void setFieldValueByName(String fieldName, Object fieldValue, Object o) throws Exception {
        Field field = getFieldByName(fieldName, o.getClass());
        if(field == null) {
            throw new Exception(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
        } else {
            field.setAccessible(true);
            Class fieldType = field.getType();
            if(String.class == fieldType) {
                field.set(o, String.valueOf(fieldValue));
            } else if(Integer.TYPE != fieldType && Integer.class != fieldType) {
                if(Long.TYPE != fieldType && Long.class != fieldType) {
                    if(Float.TYPE != fieldType && Float.class != fieldType) {
                        if(Short.TYPE != fieldType && Short.class != fieldType) {
                            if(Double.TYPE != fieldType && Double.class != fieldType) {
                                if(Character.TYPE == fieldType) {
                                    if(fieldValue != null && fieldValue.toString().length() > 0) {
                                        field.set(o, Character.valueOf(fieldValue.toString().charAt(0)));
                                    }
                                } else if(Date.class == fieldType) {
                                    field.set(o, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(fieldValue.toString()));
                                } else {
                                    field.set(o, fieldValue);
                                }
                            } else {
                                field.set(o, Double.valueOf(fieldValue.toString()));
                            }
                        } else {
                            field.set(o, Short.valueOf(fieldValue.toString()));
                        }
                    } else {
                        field.set(o, Float.valueOf(fieldValue.toString()));
                    }
                } else {
                    field.set(o, Long.valueOf(fieldValue.toString()));
                }
            } else {
                field.set(o, Integer.valueOf(Integer.parseInt(fieldValue.toString())));
            }

        }
    }

    public static void setColumnAutoSize(WritableSheet ws, int extraWith) {
        for(int i = 0; i < ws.getColumns(); ++i) {
            int colWith = 0;

            for(int j = 0; j < ws.getRows(); ++j) {
                String content = ws.getCell(i, j).getContents().toString();
                int cellWith = content.length();
                if(colWith < cellWith) {
                    colWith = cellWith;
                }
            }

            ws.setColumnView(i, colWith + extraWith);
        }

    }

    public static <T> void fillSheet(WritableSheet sheet, List<T> list, LinkedHashMap<String, String> fieldMap, int firstIndex, int lastIndex) throws Exception {
        String[] enFields = new String[fieldMap.size()];
        String[] cnFields = new String[fieldMap.size()];
        int count = 0;

        for(Iterator rowNo = fieldMap.entrySet().iterator(); rowNo.hasNext(); ++count) {
            Map.Entry index = (Map.Entry)rowNo.next();
            enFields[count] = (String)index.getKey();
            cnFields[count] = (String)index.getValue();
        }

        int var15;
        for(var15 = 0; var15 < cnFields.length; ++var15) {
            Label var16 = new Label(var15, 0, cnFields[var15]);
            sheet.addCell(var16);
        }

        var15 = 1;

        for(int var17 = firstIndex; var17 <= lastIndex; ++var17) {
            Object item = list.get(var17);

            for(int i = 0; i < enFields.length; ++i) {
                Object objValue = getFieldValueByNameSequence(enFields[i], item);
                String fieldValue = objValue == null?"":objValue.toString();
                Label label = new Label(i, var15, fieldValue);
                sheet.addCell(label);
            }

            ++var15;
        }

        setColumnAutoSize(sheet, 5);
    }

    public static <T> void fillSheet(WritableSheet sheet, List<T> list, LinkedHashMap<String, String> normalFieldMap, String collectionFieldName, String title, String content, int firstIndex, int lastIndex) throws Exception {
        String[] enFields = new String[normalFieldMap.size()];
        String[] cnFields = new String[normalFieldMap.size()];
        int count = 0;

        for(Iterator firstItem = normalFieldMap.entrySet().iterator(); firstItem.hasNext(); ++count) {
            Map.Entry childList = (Map.Entry)firstItem.next();
            enFields[count] = (String)childList.getKey();
            cnFields[count] = (String)childList.getValue();
        }

        for(int var22 = 0; var22 < cnFields.length; ++var22) {
            Label var24 = new Label(var22, 0, cnFields[var22]);
            sheet.addCell(var24);
        }

        Object var23 = list.get(0);
        List var25 = (List)getFieldValueByName(collectionFieldName, var23);
        int colCount = cnFields.length;

        Object item;
        for(Iterator rowNo = var25.iterator(); rowNo.hasNext(); ++colCount) {
            Object index = rowNo.next();
            item = getFieldValueByNameSequence(title, index);
            String currentList = item == null?"":item.toString();
            Label i = new Label(colCount, 0, currentList);
            sheet.addCell(i);
        }

        int var26 = 1;

        for(int var27 = firstIndex; var27 <= lastIndex; ++var27) {
            item = list.get(var27);

            for(int var28 = 0; var28 < enFields.length; ++var28) {
                Object var30 = getFieldValueByNameSequence(enFields[var28], item);
                String objValue = var30 == null?"":var30.toString();
                Label fieldValue = new Label(var28, var26, objValue);
                sheet.addCell(fieldValue);
            }

            if(collectionFieldName != null && !collectionFieldName.equals("")) {
                List var29 = (List)getFieldValueByName(collectionFieldName, item);

                for(int var31 = 0; var31 < var29.size(); ++var31) {
                    Object var32 = getFieldValueByNameSequence(content, var29.get(var31));
                    String var33 = var32 == null?"":var32.toString();
                    Label label = new Label(var31 + cnFields.length, var26, var33);
                    sheet.addCell(label);
                }
            }

            ++var26;
        }

        setColumnAutoSize(sheet, 5);
    }
}
