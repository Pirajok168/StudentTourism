//
//  PopulationPlace.swift
//  iosApp
//
//  Created by Данила Еремин on 18.02.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PopulationPlace: View {
    let image: String
    let score: String
    let title: String
    let city: String
    
    
    var body: some View {
      
       
        ZStack{
            VStack{
                AsyncImage(url: URL(string: image)) { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(width: 200, height: 260)
                        .contentShape(Rectangle())
                } placeholder: {
                    ProgressView()
                }
               
                   
            }
            .clipShape(RoundedRectangle(cornerRadius: 25.0))
          
           
           

        }
        .frame(width: 200, height: 260)
        

        .overlay(alignment: .topLeading, content: {
            HStack{
                Image(systemName: "star.fill")
                    .foregroundColor(.yellow)
                    .padding(.vertical, 8)
                    .padding(.leading, 8)

                Text(score)
                    .padding(.vertical, 8)
                    .padding(.trailing, 8)
            }
            .background(.ultraThinMaterial)
            .clipShape(RoundedRectangle(cornerRadius: 15.0))
            .padding()

        })

        .overlay(alignment: .bottom, content: {
            VStack{
                Text(title)
                    .font(.system(size: 12))
                    .padding(.horizontal, 5)
                    .padding(.top, 5)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .fontWeight(.semibold)
                    .padding(.bottom, 1)
                    .lineLimit(2)
                
                
                HStack {
                    Label(city, systemImage:  "location")
                        .font(.system(size: 12))
                        .padding(.bottom, 5)
                        .padding(.horizontal, 5)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .font(.subheadline)
                        .fontWeight(.thin)
                   
                    
                    
                }
               
                    
            }
            
            .background(.ultraThinMaterial, in: RoundedRectangle(cornerRadius: 15.0))
            .padding(.bottom)
            .padding(.horizontal)
        })

            
        
        
       
           
    }
}

struct PopulationPlace_Previews: PreviewProvider {
    static var previews: some View {
        HStack{
            PopulationPlace(image: "https://stud-files.sabir.pro/files/PtA4pFzxry-f1e50fcce61fcf319452f4e94f18d0171ff78119185502ff72842ead96356670.png", score: "4.5", title: "Студенческое общежитие ПВГУС", city: "Улан-Удэ")
            PopulationPlace(image: "https://stud-files.sabir.pro/files/PtA4pFzxry-f1e50fcce61fcf319452f4e94f18d0171ff78119185502ff72842ead96356670.png", score: "4.5", title: "Студенческое общежитие ПВГУС", city: "Улан-Удэ")
            
           
        }
      
            
            
    }
}

