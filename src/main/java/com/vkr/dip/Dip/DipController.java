package com.vkr.dip.Dip;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.vkr.dip.Core.Core;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
public class DipController
{
    @GetMapping("/home")
    public String home(
            @RequestParam(name="name",required=false,defaultValue="1") String name, Model model)
    {
        return "unknown request!";

    }

    @GetMapping
    public String test() throws WriterException, IOException, NotFoundException
    {
        Core core = new Core();

        return core.main("fileurl");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject link(@RequestBody JSONObject scan) throws IOException, WriterException, NotFoundException
    {
        //System.out.println(scan);
        String url = scan.get("scan").toString();
        Core core = new Core();
        System.out.println("local url: " + url);

        //String[] responses = core.main(url).split(":");
        String[] responses = core.altMain(url).split(":");

        JSONObject responser = new JSONObject();
        responser.put("type",responses[0]);
        responser.put("info",responses[1]);

        System.out.println(responser);

        return responser;

    }

}
