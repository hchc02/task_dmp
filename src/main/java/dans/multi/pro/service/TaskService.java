package dans.multi.pro.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dans.multi.pro.dto.JobDto;
import dans.multi.pro.dto.ResponseDto;

@Service
public class TaskService {
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper om =  new ObjectMapper();

    public ResponseDto taskNo1() {
        String url = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        List<JobDto> jobsData = null;
        try {
            jobsData = om.readValue(response.getBody(), new TypeReference<List<JobDto>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseDto(response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase(),jobsData);
    }

    public ResponseDto taskNo2(String id) {
        String url = "http://dev3.dansmultipro.co.id/api/recruitment/positions/"+id+"";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        JobDto jobsDetail = null;
        try {
            jobsDetail = om.readValue(response.getBody(), JobDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseDto(response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase(),jobsDetail);
    }
}
