package tests.workers.dao.survey;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mysql.cj.core.conf.url.ConnectionUrlParser;
import main.exceptions.AqualityException;
import main.model.db.dao.survey.QuestionDao;
import main.model.dto.survey.QuestionDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.workers.dao.IDaoTest;

import java.util.ArrayList;
import java.util.List;

import static utils.Validations.assertSQLToParams;

public class QuestionDaoTest extends QuestionDao implements IDaoTest {

    private String currentSql;
    private List<ConnectionUrlParser.Pair<String, String>> currentParameters;
    private List<QuestionDto> resultList;

    @BeforeMethod
    public void cleanUpResults(){
        resultList = new ArrayList<>();
    }


    @Override
    public void searchAllTest() throws AqualityException {

    }

    @Test
    public void insertTest() throws AqualityException {
        resultList.add(new QuestionDto());
        create(new QuestionDto());
        assertSQLToParams(currentSql, currentParameters);
    }

    @Override
    public void removeTest() throws AqualityException {

    }

    @Override
    protected JSONArray CallStoredProcedure(String sql, List<ConnectionUrlParser.Pair<String, String>> parameters){
        currentSql = sql;
        currentParameters = parameters;
        try {
            return new JSONArray(dtoMapper.serialize(resultList));
        } catch (JSONException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }
}
