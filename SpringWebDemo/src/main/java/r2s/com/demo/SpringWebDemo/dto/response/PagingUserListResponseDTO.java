package r2s.com.demo.SpringWebDemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import r2s.com.demo.SpringWebDemo.entity.User;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingUserListResponseDTO implements Serializable {

    private List<User> userList;

    private Integer size;

    private Integer page;

    private String sort;
}
