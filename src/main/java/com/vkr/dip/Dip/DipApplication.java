package com.vkr.dip.Dip;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DipApplication
{
	public static void main(String[] args) throws WriterException, IOException, NotFoundException
	{
		SpringApplication.run(DipApplication.class, args);
	}

}
