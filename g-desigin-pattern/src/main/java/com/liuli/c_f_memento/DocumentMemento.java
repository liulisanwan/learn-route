package com.liuli.c_f_memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// 这是基础的文档类
@Data
@AllArgsConstructor
@NoArgsConstructor
class Document{


    /**
     * 文档内容
     */
    String content;
}







/**
 * @author pcc
 */
public class DocumentMemento {


    /**
     * 文档暂存时的时间，用于恢复文档的历史状态
     */
    LocalDate dateTime;


    /**
     * 暂存文档的集合，真实场景都是数据库或者缓存
     */
    Map<LocalDate,Document> map = new HashMap<>();

    /**
     * 暂存文档
     * @param document 文档
     */
    public void docuMemento(Document document,LocalDate dateTime){

        map.put(dateTime,new Document(document.getContent()));
    }

    /**
     * 获取对应时间的文档信息
     * 恢复历史记录
     */
    public Document getDocuMemento(LocalDate dateTime){
        return map.get(dateTime);
    }
}