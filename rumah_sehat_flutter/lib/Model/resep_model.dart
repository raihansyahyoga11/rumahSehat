class Resep {
  final int id;
  final bool isDone;
  final DateTime createdAt;
  // final String namaDokter;

  Resep({
    required this.id,
    required this.isDone,
    required this.createdAt,
    // required this.namaDokter,
  });

  factory Resep.fromJson(Map<String, dynamic> json) => Resep(
        id: json["kode"],
        isDone: json['isDone'] == "true" ? true : false,
        createdAt: DateTime.parse(json['waktuAwal']),
        // namaDokter: json['namaDokter'],
      );

  Map<dynamic, dynamic> toJson() => {
        id: id,
        isDone: isDone,
        createdAt: createdAt,
        // namaDokter: namaDokter,
      };
}
