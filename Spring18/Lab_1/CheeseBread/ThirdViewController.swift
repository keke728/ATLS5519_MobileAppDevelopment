//
//  ThirdViewController.swift
//  CheeseBread
//  updates
//  Created by Keke Wu on 1/29/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit
import AudioToolbox

class ThirdViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    private var images:[UIImage]!
    
    private var winSoundID: SystemSoundID = 0
    private var runSoundID: SystemSoundID = 0
    
    @IBOutlet weak var picker: UIPickerView!
    @IBOutlet weak var winLabel: UILabel!
    @IBOutlet weak var spinButton: UIButton!
    @IBAction func spin(_ sender: UIButton) {
        var win = false
        var numInRow = -1
        var lastVal = -1
        
        for i in 0..<5 {
            let newValue = Int(arc4random_uniform(UInt32(images.count)))
            if newValue == lastVal {
                numInRow += 1
            }else{
                numInRow = 1
            }
            lastVal = newValue
            picker.selectRow(newValue, inComponent: i, animated: true)
            picker.reloadComponent(i)
            if numInRow >= 3 {
                win = true
            }
        }
        
        if runSoundID == 0 {
            let soundURL = Bundle.main.url(forResource: "run", withExtension: "wav")! as CFURL
            AudioServicesCreateSystemSoundID(soundURL, &runSoundID)
        }
        AudioServicesPlaySystemSound(runSoundID)
        
        if win {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.5){
                self.playWinSound()
            }
        }else{
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.5){
                self.showButton()
            }
        }
        spinButton.isHidden = true
        winLabel.text = " "
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 5
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return images.count
    }
    
    func pickerView(_ pickerView: UIPickerView, viewForRow row: Int, forComponent component:Int, reusing view:UIView?) -> UIView {
        let image = images[row]
        let imageView = UIImageView(image: image)
        return imageView
    }
    
    func pickerView(_ pickerView: UIPickerView, rowHeightForComponent component: Int) -> CGFloat {
        return 64
    }
    
    func showButton(){
        spinButton.isHidden = false
    }
    
    func playWinSound(){
        if winSoundID == 0 {
            let soundURL = Bundle.main.url(forResource: "tada", withExtension: "wav")! as CFURL
            AudioServicesCreateSystemSoundID(soundURL, &winSoundID )
        }
        AudioServicesPlaySystemSound(winSoundID)
        winLabel.text = "You Win!"
        DispatchQueue.main.asyncAfter(deadline: .now() + 1.5 ){
            self.showButton()
        }
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        images = [
        UIImage(named:"donut1")!,
        UIImage(named:"donut2")!,
        UIImage(named:"donut3")!,
        UIImage(named:"donut4")!,
        UIImage(named:"donut5")!,
        UIImage(named:"donut6")!
        ]
        arc4random_stir()
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
