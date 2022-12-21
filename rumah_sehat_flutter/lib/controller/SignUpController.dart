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
  final TextEditingController roleController = TextEditingController();

  Future<int> attemptSignUp(String username, String password, String name,
      String email, String umur, String role) async {
    const url = 'https://apap-065.cs.ui.ac.id/api/mobile/signupPasien';

    var response = await http.post(Uri.parse(url),
        headers: {'Content-Type': 'application/json; charset=UTF-8'},
        body: jsonEncode({
          "username": usernameController.text,
          "password": passwordController.text,
          "nama": nameController.text,
          "email": emailController.text,
          "umur": umurController.text,
          "role": roleController.text
        }));
    if (response.statusCode == 200) {
      var loginMaterial = json.decode(response.body);
      SharedPreferences prefrences = await SharedPreferences.getInstance();

      prefrences.setString('username', loginMaterial['username']);

      return response.statusCode;
    } else {
      return response.statusCode;
    }
  }
}
