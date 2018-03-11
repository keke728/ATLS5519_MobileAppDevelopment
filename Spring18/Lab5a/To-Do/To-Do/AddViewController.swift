//
//  AddViewController.swift
//  To-Do
//
//  Created by Keke Wu on 3/10/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

class AddViewController: UIViewController {
    @IBOutlet weak var todoname: UITextField!
    @IBOutlet weak var todoURL: UITextField!
    
    var addedToDo = String()
    var addedURL = String()
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "saveSegue"{
            if todoname.text?.isEmpty == false{
                addedToDo = todoname.text!
                addedURL = todoURL.text!
            }

        }
    }
}
