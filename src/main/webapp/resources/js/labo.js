/**
 * 
 */

		$(document).ready(function(){ 
			
			var global_users=[]  ;
			var global_projects =[];
			
			if($(location).attr('href') == 'http://localhost:8080/labo/task.jsf'){
				
				/*
				 * Getting the list of projects
				 * */
				$.get("rest/projects", function(xml) {

				    var data_list = $("#project_list");
				    data_list.empty();
				    global_projects=0;
				    data_list.append(' <option value=""> select project </option> ');
				     
					var data = xml.documentElement.getElementsByTagName("project");
				    for(var i = 0; i < data.length; i++) {

				    	var xdata = data[i];
				    	data_list.append("<option value='" + $(xdata).find("name").text() +"'>"+$(xdata).find("name").text() + " </option>");
				    	global_projects[i] = $(xdata).find("name").text() ;
				    }
	       
			     });
				
				/*
				 * Getting the list of users
				 * */
				$.get("rest/members", function(xml) {

				    var data_list = $("#user_list");
				    data_list.empty();
				    global_users = [];
				    data_list.append(' <option value=""> select user </option> ');
				     
					var data = xml.documentElement.getElementsByTagName("member");
				    for(var i = 0; i < data.length; i++) {

				    	var xdata = data[i];
				    	data_list.append("<option value='" + $(xdata).find("id").text() +"'>"+$(xdata).find("name").text() + " </option>");
				    	global_users.push( $(xdata).find("name").text() );
				    }
	       
			   });
				
				
			  
				
			}
			
			
			/*
			 * here we feed the hidden element based on selected project/user when registering a task
			 * */
			
 
			
			$("#user_list").change(function(){
				 
				var selectedName = $("#user_list :selected" ).text();
				  $("#user_id").val(selectedName)  ;
			 });
			
			$("#project_list").change(function(){
				 
				var selectedName = $("#project_list :selected" ).text();
				  $("#project_id").val(selectedName)  ;
			 });
			
			
			
			
			
			
			
			$("#teamView").click(function(){
				
				
					 
					$.get("rest/teams", function(xml) {

				    var listOfEvents = $("#listOfItems");
				    listOfEvents.empty();
				    $("#reg").hide();

					var data = xml.documentElement.getElementsByTagName("team");
				    for(var i = 0; i <= data.length; i++) {

				    	var xdata = data[i]
				         listOfEvents.append("<li><a href='#'>" + $(xdata).find("name").text() + "</a>");
		
				    }
					

				    listOfEvents.listview("refresh");
				    listOfEvents.show();
				    });
					
			
				
			});
			
			
		}); 