package app.bm.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ViewGroup {
    Long id;
    String name;
    ViewUser leader;
    List<ViewUser> members;
}
