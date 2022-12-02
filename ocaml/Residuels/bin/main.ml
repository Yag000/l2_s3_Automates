type regex =
  | None
  | Epsilon
  | Letter of char
  | Union of regex * regex
  | Concatenation of regex * regex
  | KleeneStar of regex

let rec print_regex = function
  | None -> ()
  | Epsilon -> ()
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

let rec eps = function
  | None -> false
  | Epsilon -> true
  | Letter _ -> false
  | Union (r1, r2) -> eps r1 || eps r2
  | Concatenation (r1, r2) -> eps r1 && eps r2
  | KleeneStar _ -> true

let rec residual_letter letter = function
  | None -> None
  | Epsilon -> None
  | Letter c -> if c = letter then Epsilon else None
  | Union (r1, r2) ->
      let r1' = residual_letter letter r1 in
      let r2' = residual_letter letter r2 in
      Union (r1', r2')
  | Concatenation (r1, r2) ->
      let r1' = residual_letter letter r1 in
      if eps r1 then
        let r2' = residual_letter letter r2 in
        Union (Concatenation (r1', r2), r2')
      else Concatenation (r1', r2)
  | KleeneStar r -> Concatenation (residual_letter letter r, KleeneStar r)

let () =
  let r =
    Concatenation
      ( Concatenation
          ( Concatenation
              (Letter 'a', KleeneStar (Union (Letter 'b', Letter 'c'))),
            Epsilon ),
        None )
  in
  print_regex r;
  print_newline ();
  residual_letter 'a' r |> print_regex;
  residual_letter 'a' r |> residual_letter 'a' |> print_regex
