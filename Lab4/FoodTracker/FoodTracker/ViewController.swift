//
//  ViewController.swift
//  FoodTracker
//
//  Created by Keke Wu on 10/12/17.
//  Copyright © 2017 Keke Wu. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    var user = Food()
    let filename = "food.plist"
    
    @IBOutlet weak var breakfastLabel: UILabel!
    @IBOutlet weak var lunchLabel: UILabel!
    @IBOutlet weak var dinnerLabel: UILabel!
    
    @IBAction func unwindSegue(_ segue:UIStoryboardSegue){
        breakfastLabel.text = user.breakfastRec
        lunchLabel.text = user.lunchRec
        dinnerLabel.text = user.dinnerRec
    }
    
    func docFilePath(_ filename: String)-> String?{
      let path = NSSearchPathForDirectoriesInDomains(FileManager.SearchPathDirectory.documentDirectory,FileManager.SearchPathDomainMask.allDomainsMask, true)
      let dir = path[0] as NSString
      print(dir.appendingPathComponent(filename))
      return dir.appendingPathComponent(filename)
    }
    override func viewDidLoad() {
        let filePath = docFilePath(filename)
        if FileManager.default.fileExists(atPath: filePath!){
            let path = filePath
            let dataDictionary = NSDictionary(contentsOfFile: path!) as![String:String]
            if dataDictionary.keys.contains("breakfast"){
                user.breakfastRec = dataDictionary["breakfast"]
                breakfastLabel.text = user.breakfastRec
            }
            
            if dataDictionary.keys.contains("lunch"){
                user.lunchRec = dataDictionary["lunch"]
                lunchLabel.text = user.lunchRec
            }
            
            if dataDictionary.keys.contains("dinner"){
               user.dinnerRec = dataDictionary["dinner"]
                dinnerLabel.text = user.dinnerRec
            }
        }
            let app = UIApplication.shared
            NotificationCenter.default.addObserver(self, selector: #selector(UIApplicationDelegate.applicationWillResignActive(_:)), name:NSNotification.Name(rawValue:"UIApplicationWillResignActiveNotification"),object: app)
    
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    func applicationWillResignActive(_ notification: Notification){
         let filePath = docFilePath(filename)
         let data = NSMutableDictionary()
         if user.breakfastRec != nil{
            data.setValue(user.breakfastRec, forKey:"breakfast")
        }
        if user.lunchRec != nil{
           data.setValue(user.lunchRec, forKey: "lunch")
        }
        if user.dinnerRec != nil{
            data.setValue(user.dinnerRec, forKey: "dinner")
        }
        data.write(toFile: filePath!, atomically: true)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

