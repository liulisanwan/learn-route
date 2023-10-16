package com.liuli.c_f_memento;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author pcc
 */
public class TestMemento {
    public static void main(String[] args) {
        DocumentMemento documentMemento = new DocumentMemento();
        Document  document = new Document();
        document.setContent("北国风光，万");
        saveTmp(document,documentMemento,LocalDate.now()); // 暂存一次
        document.setContent("北国风光，万里雪飘");
        saveTmp(document,documentMemento,LocalDate.now().plusDays(1)); // 暂存一次
        document.setContent("北国风光，万里雪飘，千里冰封");
        saveTmp(document,documentMemento,LocalDate.now().plusDays(2)); // 暂存一次
        System.out.println("当前文档为："+document.getContent());


        // 假设有一个页面，我们选择了恢复到时间点为第二条的数据
        Document docuMemento = documentMemento.getDocuMemento(LocalDate.now().plusDays(1));
        System.out.println("恢复文档为："+docuMemento.getContent());

    }

    static void saveTmp(Document document,DocumentMemento documentMemento,LocalDate localDate){
        Optional.ofNullable(document).ifPresent(docume->{
            documentMemento.docuMemento(docume, localDate);
        });
    }
}