package r2s.com.demo.SpringWebDemo.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class PageResponseDTO {
    private List<?> data;

    private Integer size;

    private Integer page;

    private long totalRecord;

    private int totalPages;
}
