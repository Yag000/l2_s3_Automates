(* Automate deterministe*)

type dfa = {
    alpbahet : char list; 
    states : string list; 
    initial_state: string; 
    acceptant_states: string list; 
    sigma : int * char -> int
    }
;;

let runDet automate word =
    let rec runDetRec state word =
        match word with
        | [] -> state
        | x::xs -> runDetRec (automate.sigma (state, x)) xs
    in List.mem (runDetRec  automate.initial_state word) automate.acceptant_states
;;
  

let a_pair = function
    |("0",'a') -> 1
    |("1",'a') -> 0
    |_ -> -1
;;

let automateA_pair = {
    alpbahet = ['a'];
    states = ["0";"1"];
    initial_state = "0";
    acceptant_states = ["1"];
    sigma = a_pair
}
;;



let rec create_a_list n =
    if n = 0 then []
    else 'a'::(create_a_list (n-1))
;;

let rec create_a_list_list n = 
    if n = 0 then []
    else (create_a_list n)::(create_a_list_list (n-1))
;;



(* Automate non-deterministe*)

type ndfa = {
    alpbahet : char list; 
    states : string list; 
    initial_states: string list; 
    acceptant_states: string list; 
    sigma : int * char -> int list
    }
;;

let runNonDet automate word =
    let rec runNonDetRec state word =
        match word with
        | [] -> [state]
        | x::xs -> List.flatten (List.map (fun s -> 
                runNonDetRec s xs) (automate.sigma (state,x)))
    in let all_last_states =
        List.flatten (List.map (fun s -> 
            runNonDetRec s word) automate.initial_states) in
            List.exists (fun x -> List.mem x automate.acceptant_states ) all_last_states
;;

let get_dfa_function_triplet (state,letter) =
    (state,letter,sigma (satte,letter))

let get_all_dfa_function_triplets automate =
    List.fold_left (
        fun s acc -> 
            let all_states = List.map (fun x ->
                get_dfa_function_triplet (s,x) ) automate.alpbahet 
            in
            
    )

let ndfa_to_dfa automate =
    let dfa = {
        alpbahet = ['a'];
        states = [0;1;2];
        initial_states = [0];
        acceptant_states = [1];
        sigma = a_pair
    }
   
;;

let last_a = function
    |("0",'a') -> ["0";"1"]
    |("0",'b') -> ["0"]
    | _ -> ["-1"]
;;

let automateA_last = {
    alpbahet = ['a';'b'];
    states = ["0";"1"];
    initial_states = ["0"];
    acceptant_states = ["1"];
    sigma = last_a
}

let print_word = List.iter (fun x -> Printf.printf "%c" x)

let test_list=
    [[];
    ['a';'b'];
    ['a';'a'];
    ['b';'b'];
    ['b';'b'];
    ['a';'a';'b'];
    ['a';'a';'a'];
    ['a';'b';'a'];
    ['a';'b';'b'];
    ['b';'a';'a'];
    ['b';'a';'b'];
    ['b';'b';'a'];
    ['b';'b';'b'];
    ['a';'a';'b';'b'];
    ['a';'a';'b';'a'];
    ['a';'a';'b';'b'];
    ['a';'a';'b';'a'];
    ['a';'a';'a';'a'];
    ['a';'a';'a';'b'];
    ['a';'a';'b';'a';'a'];
    ['a';'a';'b';'a';'b'];
    ['a';'a';'b';'b';'a'];
    ['a';'a';'b';'b';'b'];
    ['a';'a';'a';'a';'a'];
    ['a';'a';'a';'a';'b'];
    ['a';'a';'a';'b';'a'];
    ['a';'a';'a';'b';'b'];
    ['a';'a';'b';'a';'a';'a'];
    ['a';'a';'b';'a';'a';'b'];
    ['a';'a';'b';'a';'b';'a'];
    ['a';'a';'b';'a';'b';'b'];
    ['a';'a';'b';'b';'a';'a'];
    ['a';'a';'b';'b';'a';'b'];
    ['a';'a';'b';'b';'b';'a'];
    ['a';'a';'b';'b';'b';'b'];
    ['a';'a';'a';'a';'a';'a'];
    ['a';'a';'a';'a';'a';'b'];
    ['a';'a';'a';'a';'b';'a'];
    ['a';'a';'a';'a']
    ]

;;

let rec test_list_list list_list =
    List.for_all (fun x -> runDet automateA_pair x = (List.length x mod 2 = 1)) list_list
;;


let de


(* Main *)
let () =
    if test_list_list (create_a_list_list 10) then print_endline("True") else print_endline("False");
    print_endline("NonDet test");
    List.iter (fun x -> print_word x ;
        print_string (" : ");
        if runNonDet automateA_last x then print_endline("True") else print_endline("False")) test_list
    


;;
