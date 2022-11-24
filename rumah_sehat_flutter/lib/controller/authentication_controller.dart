import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class AuthenticationController {
  String? token;
  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();


  Future<int> loginUser() async {
    const url = 'http://172.20.10.6:8080/api/mobile/signin';
   print("haha");
    var response = await http.post(Uri.parse(url),
        headers: {'Content-Type': 'application/json; charset=UTF-8'},
        body: jsonEncode({
          "username": usernameController.text,
          "password": passwordController.text,
        }));
    print("huhe");
    if (response.statusCode == 200) {
      print("masuk 200");
      var loginMaterial = json.decode(response.body);
      token = loginMaterial['token'];
      SharedPreferences prefrences = await SharedPreferences.getInstance();


      prefrences.setString('token', loginMaterial['token']);
      prefrences.setString('username', loginMaterial['username']);

      return response.statusCode;
    } else {
      return response.statusCode;
    }
  }

  logOut() async {
    SharedPreferences prefrences = await SharedPreferences.getInstance();
    prefrences.remove("token");
    token = null;
    print(token);
    prefrences.remove("token");
  }
}
