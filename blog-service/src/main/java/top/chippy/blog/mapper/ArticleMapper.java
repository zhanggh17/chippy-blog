package top.chippy.blog.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import top.chippy.blog.entity.Article;

import java.util.List;

/**
 * @program: chippy-blog
 * @Date: 2019/1/4 14:22
 * @Author: chippy
 * @Description:
 */
public interface ArticleMapper extends Mapper<Article> {

    List<Article> list(@Param("search") String search);

    List<Article> news(@Param("num") int num);

    List<Article> hots(@Param("num") int num);

    List<Article> relation(@Param("type") String type, @Param("num") int num);

    Article single(@Param("id") String id);

    @Update("UPDATE chippy_article SET `count`= `count` + 1 WHERE id = #{id}")
    void updateArticleReading(@Param("id") String id);

    @Select("SELECT * FROM chippy_article WHERE `type` = #{type} AND article_no = #{articleNo}-1")
    Article preArticle(@Param("type") String type,
                       @Param("articleNo") String articleNo);

    @Select("SELECT * FROM chippy_article WHERE `type` = #{type} AND article_no = #{articleNo}+1")
    Article posArticle(@Param("type")String type,
                       @Param("articleNo")String articleNo);
}
