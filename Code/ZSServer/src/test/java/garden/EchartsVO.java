package garden;

import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

public class EchartsVO {
    //源
    private String subject;
    //路线
    private String object;

    private String relation;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EchartsVO echartsVO = (EchartsVO) o;
        return Objects.equals(subject, echartsVO.subject) &&
                Objects.equals(object, echartsVO.object) &&
                Objects.equals(relation, echartsVO.relation);
    }

    @Override
    public int hashCode() {

        return Objects.hash(subject, object);
    }

    public String getSubject() {

        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public boolean isSame(EchartsVO vo){
        if(vo.object.equals(this.object) && vo.subject.equals(this.subject)){
            return true;
        }
        return false;
    }
}
