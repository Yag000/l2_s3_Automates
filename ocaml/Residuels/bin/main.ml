type regex =
  | None
  | Letter of char
  | Union of regex * regex
  | Concatenation of regex * regex
  | KleeneStar of regex

let rec print_regex = function
  | None -> print_string "None"
  | Letter c -> print_char c
  | Union (r1, r2) ->
      print_string "(";
      print_regex r1;
      print_string " + ";
      print_regex r2;
      print_string ")"
  | Concatenation (r1, r2) ->
      print_string "(";
      print_regex r1;
      print_regex r2;
      print_string ")"
  | KleeneStar r ->
      print_string "(";
      print_regex r;
      print_string ")*"

let () =
  let r =
    Concatenation (Letter 'a', KleeneStar (Union (Letter 'b', Letter 'c')))
  in
  print_regex r;
  print_newline ()
