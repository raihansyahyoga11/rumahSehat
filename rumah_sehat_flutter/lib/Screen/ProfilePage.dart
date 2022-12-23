import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/Model/PasienModel.dart';
import 'package:rumah_sehat_flutter/controller/authentication_controller.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'TopUpPage.dart';





class ProfilePage extends StatefulWidget {

  @override
  State<ProfilePage> createState() => _ProfilePageState();

}

class _ProfilePageState extends State<ProfilePage> {
  late Future<PasienModel> futureAlbum;

  @override
  void initState() {
    super.initState();
    AuthenticationController profileController= AuthenticationController();
    futureAlbum = AuthenticationController().getUserProfile();

  }

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      title: 'User Profile',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ), debugShowCheckedModeBanner: false,
      home: Scaffold(
      body:SizedBox(
          width: double.infinity,
          child:
          FutureBuilder<PasienModel>(
            future: futureAlbum,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
            return ListView(
              padding: const EdgeInsets.all(8),
              children: <Widget>[
                Container(
                  height: 100,
                  child: Center(child: Icon(Icons.male)),
                ),
                Container(
                  height: 60,
                  child: ListTile(
                      title: Text(
                          'Nama',
                           style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 12
                        )
                      ),
                      subtitle: Text(
                        snapshot.data!.nama,
                        style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize:18
                        )
                      )
                  ),
                ),
                Container(
                  height: 60,
                  child: ListTile(
                      title: Text(
                          'Username',
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 12
                          )
                      ),
                      subtitle: Text(
                          snapshot.data!.username,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize:18
                          )
                      )
                  ),
                ),
                Container(
                  height: 60,
                  child: ListTile(
                      title: Text(
                          'Email',
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 12
                          )
                      ),
                      subtitle: Text(
                          snapshot.data!.email,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 18
                          )
                      )
                  ),
                ),
                Container(
                  height: 10
                ),
                Column(
                  children: [
                    ListTile(
                      title: Text(
                          'Saldo',
                          textAlign: TextAlign.center,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 20
                          )
                      ),
                      subtitle: Text(
                          snapshot.data!.saldo.toString(),
                          textAlign: TextAlign.center,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 30
                          )
                      )
                    ),Container(height: 30,
                        width: 120,
                        decoration: BoxDecoration(
                        color: Colors.blue, borderRadius: BorderRadius.circular(30)),
                        child: ElevatedButton (onPressed: () {Navigator.push(context,
                        MaterialPageRoute(builder: (context) => TopUpPage())); },
                          child: Text(
                          'TOP UP',
                          style: TextStyle(color: Colors.white, fontSize: 16),
                          )
                        ),
                      ),
                  ]
                ),
              ],
             );
            } else if (snapshot.hasError) {
                Text('${snapshot.error}');

             }

            //By default, show a loading spinner.
              return Text('${snapshot.error}');
            }),
        ),
      )
    );
  }

}

