//
//  RecordViewController.swift
//  Daylight
//
//  Created by Keke Wu on 10/14/17.
//  Copyright © 2017 Keke Wu. All rights reserved.
//

import UIKit

class RecordViewController: UIViewController {

    @IBOutlet weak var moodImage: UIImageView!
    @IBOutlet weak var breakfastLabel: UILabel!
    @IBOutlet weak var lunchLabel: UILabel!
    @IBOutlet weak var dinnerLabel: UILabel!
    var user = MealHighlight()
    var newImage : UIImage!
    
    override func viewDidLoad() {
        breakfastLabel.text = user.breakfastHighlight
        lunchLabel.text = user.lunchHighlight
        dinnerLabel.text = user.dinnerHighlight
        
        super.viewDidLoad()
        moodImage.image = newImage

        // Do any additional setup after loading the view.
    }
    
  
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
