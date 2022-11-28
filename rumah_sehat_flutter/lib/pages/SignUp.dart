import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class SignUpPage extends StatefulWidget {
  const SignUpPage({Key? key}) : super(key: key);

  @override
  State<SignUpPage> createState() => _SignUpPageState();
}

class _SignUpPageState extends State<SignUpPage> {
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _umurController = TextEditingController();

  void displayDialog(context, title, text) => showDialog(
    context: context,
    builder: (context) =>
        AlertDialog(
            title: Text(title),
            content: Text(text)
        ),
  );

  Future<http.Response> attemptSignUp(String username, String password, String umur, String name, String email) async {
    var res = await http.post(
        Uri.parse('http://localhost:8080/api/v1/signup'),
        headers: <String, String>{"Content-Type": "application/json; charset=UTF-8",
          "Accept": "application/json"},
        body: jsonEncode(<String, String>{
          "nama": name,
          "email": email,
          "umur": umur,
          "username": username,
          "password": password
        })
    );
    return res;

  }

  final ButtonStyle style = ElevatedButton.styleFrom(
      padding: EdgeInsets.all(20),
      backgroundColor: Colors.lightBlueAccent,
      textStyle: TextStyle(color: Colors.white),
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ));



  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Sign Up"),),
        body: Padding(
          padding: const EdgeInsets.all(3.0),
          child: Column(
            children: [
              SizedBox(
                height: 35,
              ),
              Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 20),
                  child: Container(
                      alignment: Alignment.center,
                      padding: const EdgeInsets.all(10),
                      child: Text(
                        'Registrasi Pasien Baru',
                        style: TextStyle(fontSize: 20,
                                        fontWeight: FontWeight.bold,
                        color: Colors.grey[700]),

                      ))),
              SizedBox(
                height: 20,
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SizedBox(
                  height: 40,
                  child: TextFormField(
                    controller: _usernameController,
                    autofocus: false,
                    // initialValue: 'Rose Angela',
                    decoration: InputDecoration(
                      hintText: 'Username',
                      contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(32.0)),
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 20,
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SizedBox(
                  height: 40,
                  child: TextFormField(
                    autofocus: false,
                    //initialValue: 'some password',
                    controller: _passwordController,
                    obscureText: true,
                    decoration: InputDecoration(
                      hintText: 'Password',
                      contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(32.0)),
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 35,
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SizedBox(
                  height: 40,
                  child: TextFormField(
                    autofocus: false,
                    //initialValue: 'some password',
                    controller: _nameController,
                    decoration: InputDecoration(
                      hintText: 'Nama',
                      contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(32.0)),
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 35,
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SizedBox(
                  height: 40,
                  child: TextFormField(
                    autofocus: false,
                    //initialValue: 'some password',
                    controller: _emailController,
                    decoration: InputDecoration(
                      hintText: 'Email',
                      contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(32.0)),
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 35,
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SizedBox(
                  height: 40,
                  child: TextFormField(
                    autofocus: false,
                    //initialValue: 'some password',
                    controller: _umurController,
                    keyboardType: TextInputType.number,
                    decoration: InputDecoration(
                      hintText: 'Umur',
                      contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(32.0)),
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 35,
              ),
              Padding(
                padding: EdgeInsets.symmetric(vertical: 16.0),
                child: ElevatedButton(
                  style: style,
                  onPressed: () async {
                    var response = await attemptSignUp(_usernameController.text, _passwordController.text, _umurController.text, _nameController.text, _emailController.text);
                    print(response.statusCode);
                    if(response.statusCode == 200) {
                      displayDialog(context, "Sign Up success", "Sign Up success");
                      //JWT.fromJson(jsonDecode(jwt.body));
                      // Navigator.push(
                      //     context,
                      //     MaterialPageRoute(
                      //         builder: (context) => HomePage.fromBase64(jwt)
                      //     )
                      // );
                    } else if(response.statusCode == 400){
                      displayDialog(context, "An Error Occurred", "Request body has invalid type or missing field.");
                    } else if(response.statusCode == 403) {
                      displayDialog(context, "An Error Occurred", "Forbidden");
                    } else {
                      displayDialog(context, "An Error Occurred", "No account was found matching that username and password");
                    }
                  },
                  child: Text(
                    'Submit',
                    style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)
                  ),
                ),
              ),
            ],
          ),
        )
    );
  }
}
