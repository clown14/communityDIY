package life.majiang.community2.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-10-04 23:33
 **/

@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;

    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {

        this.totalPage = totalPage;

        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            //page左边，头插
            if (page - i > 0) {
                pages.add(0, page - i);
            }

            //page右边，尾插
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 是否展示上一页
        showPrevious = page != 1;

        // 是否展示下一页
        showNext = page != totalPage;

        // 是否展示第一页
        showFirstPage = !pages.contains(1);

        // 是否展示最后一页
        showEndPage = !pages.contains(totalPage);

    }
}

    