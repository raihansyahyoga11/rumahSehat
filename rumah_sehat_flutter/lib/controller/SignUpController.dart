import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class SignUpController {
  String? token;
  final TextEditingController usernameController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();
  final TextEditingController nameController = TextEditingController();
  final TextEditingController emailController = TextEditingController();
  final TextEditingController umurController = TextEditingController();

  // Versi Yoga
  Future<int> attemptSignUp(String username, String password, String umur, String name, String email) async {
    const url = 'http://localhost:8080/api/mobile/signupPasien';
    print("haha");
    var response = await http.post(Uri.parse(url),
        headers: {'Content-Type': 'application/json; charset=UTF-8'},
        body: jsonEncode({
          "username": usernameController.text,
          "password": passwordController.text,
          "nama" : nameController.text,
          "email" : emailController.text,
          "umur": umurController.text,

        }));
    print("huhe");
    if (response.statusCode == 200) {
      print("masuk 200");
      var loginMaterial = json.decode(response.body);
      var token = loginMaterial['token'];
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

  // Versi Docs Flutter
  // Future<http.Response> attemptSignUp(String username, String password, String umur, String name, String email) async {
  //   final response = await http.post(
  //     Uri.parse('http://localhost:8080/api/mobile/signup'),
  //     headers: <String, String>{
  //       'Content-Type': 'application/json; charset=UTF-8',
  //     },
  //     body: jsonEncode(<String, String>{
  //       "username": usernameController.text,
  //       "password": passwordController.text,
  //       "nama" : nameController.text,
  //       "email" : emailController.text,
  //       "umur": umurController.text,
  //     }),
  //   );
  //
  //   if (response.statusCode == 200) {
  //     // If the server did return a 201 CREATED response,
  //     // then parse the JSON.
  //     print("masuk 200");
  //     return jsonDecode(response.body);
  //   } else {
  //     // If the server did not return a 201 CREATED response,
  //     // then throw an exception.
  //     throw Exception('Failed to create pasien.');
  //   }
  }


