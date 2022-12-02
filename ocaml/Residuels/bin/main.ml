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

let rec is_null = function
  | None -> true
  | Epsilon -> false
  | Letter _ -> false
  | Union (r1, r2) -> is_null r1 && is_null r2
  | Concatenation (r1, r2) -> is_null r1 || is_null r2
  | KleeneStar r -> is_null r

let rec simplify = function
  | None -> None
  | Epsilon -> Epsilon
  | Letter c -> Letter c
  | Union (r1, r2) ->
      let r1 = simplify r1 in
      let r2 = simplify r2 in
      if eps r1 then if eps r2 then Epsilon else r2
      else if eps r2 then r1
      else Union (r1, r2)
  | Concatenation (r1, r2) ->
      let r1 = simplify r1 in
      let r2 = simplify r2 in
      let r1_null = is_null r1 in
      let r2_null = is_null r2 in
      if r1_null then if r2_null then None else r2
      else if r2_null then r1
      else if eps r1 then if eps r2 then Epsilon else r2
      else if eps r2 then r1
      else Concatenation (r1, r2)
  | KleeneStar r ->
      let r = simplify r in
      KleeneStar r

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

let rec residual_string s regex =
  match s with
  | "" -> Epsilon
  | s ->
      if String.length s = 1 then residual_letter s.[0] regex
      else
        let c = (String.sub s 0 1 |> String.lowercase_ascii |> String.get) 0 in
        Concatenation
          ( residual_letter c regex,
            residual_string (String.sub s 1 (String.length s - 1)) regex )

let () =
  let r =
    Concatenation
      ( Concatenation
          ( Concatenation
              (Letter 'a', KleeneStar (Union (Letter 'b', Letter 'c'))),
            Epsilon ),
        None )
  in
  print_newline ();
  print_regex r;
  print_newline ();
  residual_letter 'a' r |> print_regex;
  print_newline ();
  residual_letter 'a' r |> residual_letter 'a' |> print_regex;
  print_newline ();
  residual_letter 'a' r |> residual_letter 'a' |> simplify |> print_regex;
  print_newline ();
  residual_string "a" r |> print_regex;
  print_newline ()
