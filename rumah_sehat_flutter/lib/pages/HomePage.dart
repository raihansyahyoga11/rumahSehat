import 'package:rumah_sehat_flutter/controller/authentication_controller.dart';
import 'login.dart';

import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  AuthenticationController authenticationController = AuthenticationController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Rumah Sehat'),
      ),
      body: Center(
        child: Container(
          height: 80,
          width: 150,
          decoration: BoxDecoration(
              color: Colors.blue, borderRadius: BorderRadius.circular(10)),
          child: ElevatedButton(
            onPressed: () async {
              authenticationController.logOut();
              Navigator.of(context).pushReplacement(MaterialPageRoute(
                builder: (context) => Login(),
              ));
            },
            child: Text(
              'Log out',
              style: TextStyle(color: Colors.white, fontSize: 25),
            ),
          ),
        ),
      ),
    );
  }
}