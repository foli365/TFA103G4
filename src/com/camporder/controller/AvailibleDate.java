package com.camporder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campsitetentstatus.model.CampsiteTentStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/availibleDate")
public class AvailibleDate extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		int dateList[] = objectMapper.readValue(json, int[].class);
		CampsiteTentStatusService CTSSvc = new CampsiteTentStatusService();
		try {
			ArrayList<String> unavilibleDate = CTSSvc.unavailibleDate(dateList[0], dateList[1]);
			String unavilibleDateString = unavilibleDate.toString();
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(unavilibleDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
