package org.stone.mvc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stone.entity.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liulei on 12/8 0008.
 */
@Slf4j
@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home(Map<String, Object> map) {
        map.put("userName", "Lusa");
        return "home";
    }

    @RequestMapping("/demo1")
    public String demo1(Model model) {
        model.addAttribute("author", "Peter");
        return "demo1";
    }

    @RequestMapping(value = "/rest1", method = RequestMethod.GET)
    public
    @ResponseBody
    String rest1(@RequestParam("userName") String userName) {
        JSONObject json = new JSONObject();
        json.put("key1", "key1");
        json.put("welcome", "hello," + userName);
        return json.toJSONString();
    }

    @RequestMapping(value = "/rest2", method = RequestMethod.GET)
    public
    @ResponseBody
    String rest2() {
        JSONObject json = new JSONObject();


        JSONArray sites = new JSONArray();

        JSONObject obj1 = new JSONObject();
        obj1.put("Name", "abc");
        obj1.put("Url", "www.runoob.com");
        obj1.put("Country", "CN");
        JSONObject obj2 = new JSONObject();
        obj2.put("Name", "Google");
        obj2.put("Url", "www.google.com");
        obj2.put("Country", "USA");
        JSONObject obj3 = new JSONObject();
        obj3.put("Name", "Facebook");
        obj3.put("Url", "www.facebook.com");
        obj3.put("Country", "USA");
        JSONObject obj4 = new JSONObject();
        obj4.put("Name", "baidu");
        obj4.put("Url", "www.baidu.com");
        obj4.put("Country", "CN");

        sites.add(obj1);
        sites.add(obj2);
        sites.add(obj3);
        sites.add(obj4);

        json.put("sites", sites);
        return json.toJSONString();
    }

    @RequestMapping("/demo2")
    public String demo2(Model model) {
        Person singlePerson = new Person("Peter", 23);
        model.addAttribute("singlePerson", singlePerson);

        List<Person> people = new ArrayList<Person>();
        people.add(new Person("Lily", 22));
        people.add(new Person("Lusa", 21));
        people.add(new Person("Edison", 2));
        model.addAttribute("people", people);

        return "demo2";
    }

    /*
    {
"records":[
{"Name":"Alfreds Futterkiste","City":"Berlin","Country":"Germany"},
{"Name":"Ana Trujillo Emparedados y helados","City":"México D.F.","Country":"Mexico"},
{"Name":"Antonio Moreno Taquería","City":"México D.F.","Country":"Mexico"},
{"Name":"Around the Horn","City":"London","Country":"UK"},
{"Name":"B's Beverages","City":"London","Country":"UK"},
{"Name":"Berglunds snabbköp","City":"Luleå","Country":"Sweden"},
{"Name":"Blauer See Delikatessen","City":"Mannheim","Country":"Germany"},
{"Name":"Blondel père et fils","City":"Strasbourg","Country":"France"},
{"Name":"Bólido Comidas preparadas","City":"Madrid","Country":"Spain"},
{"Name":"Bon app'","City":"Marseille","Country":"France"},
{"Name":"Bottom-Dollar Marketse","City":"Tsawassen","Country":"Canada"},
{"Name":"Cactus Comidas para llevar","City":"Buenos Aires","Country":"Argentina"},
{"Name":"Centro comercial Moctezuma","City":"México D.F.","Country":"Mexico"},
{"Name":"Chop-suey Chinese","City":"Bern","Country":"Switzerland"},
{"Name":"Comércio Mineiro","City":"São Paulo","Country":"Brazil"}
]
}
     */

    @RequestMapping(value = "/rest3", method = RequestMethod.GET)
    public
    @ResponseBody
    String rest3() throws IOException{

        String customers = new String(Files.readAllBytes(Paths.get("src/main/resources/static/data/customer.json")),"UTF-8");

        log.info(customers);

        return customers;
    }

}
