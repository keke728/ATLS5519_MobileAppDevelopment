//
//  FirstViewController.swift
//  CheeseBread
//
//  Created by Keke Wu on 1/29/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
   
    let breadComponent = 0
    let cheeseComponent = 1
    
    var cheeseBread = [String:[String]]()
    var bread = [String]()
    var cheese = [String]()
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }
    
    @IBOutlet weak var breadPicker: UIPickerView!
    @IBOutlet weak var breadLabel: UILabel!
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == breadComponent {
            return bread.count
        }else{
            return cheese.count
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == breadComponent {
            return bread[row]
        }
        else{
            return cheese[row]
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if component == breadComponent {
            let selectedBread = bread[row]
            cheese = cheeseBread[selectedBread]!
            breadPicker.reloadComponent(cheeseComponent)
            breadPicker.selectRow(0, inComponent:cheeseComponent, animated:true)
        }
        let breadrow = pickerView.selectedRow(inComponent: breadComponent)
        let cheeserow = pickerView.selectedRow(inComponent: cheeseComponent)
        breadLabel.text = "You bread is \(bread[breadrow]) with \(cheese[cheeserow])"
        breadLabel.numberOfLines = 0
        breadLabel.lineBreakMode = .byWordWrapping
        breadLabel.textAlignment = NSTextAlignment.center
        breadLabel.sizeToFit()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let pathURL = Bundle.main.url(forResource:"Bread", withExtension:"plist"){
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL)
                cheeseBread = try plistdecoder.decode([String:[String]].self, from: data)
                bread = Array(cheeseBread.keys)
                cheese = cheeseBread[bread[0]]! as [String]
            }catch{
                print(error)
            }
        }
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

