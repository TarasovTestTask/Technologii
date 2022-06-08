import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Parser {

    public static void main(String[] args) throws IOException {
        String json = parserToJson();
        String yml = parserJsonToYml(json);
        save(yml);
    }

    private static void save(String yml) {
        try {
            File file = new File("C:\\Users\\Vlad\\Desktop\\Search\\output.yml");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(yml.toCharArray());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parserToJson() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        List entries = xmlMapper.readValue(new File("sample.xml"), List.class);
        ObjectMapper jsonMapper = new ObjectMapper();
        String json = jsonMapper.writeValueAsString(entries);
        return json;
    }

    private static String parserJsonToYml(String jsonString) throws JsonProcessingException, IOException {
        JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
        String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
        return jsonAsYaml;
    }
}

