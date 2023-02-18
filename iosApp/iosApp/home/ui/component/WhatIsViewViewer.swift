//
//  WhatIsViewViewer.swift
//  iosApp
//
//  Created by Данила Еремин on 18.02.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WhatIsViewViewer: View {
    var body: some View {
        Text("Что посмотреть?")
            .font(.title.bold())
            .padding(.horizontal)
            .padding(.top)
            .padding(.bottom)
        
        HStack{
            Spacer()
            Button {
                
            } label: {
                VStack{
                    
                    Image(systemName: "atom")
                    
                    Text("Наука")
                }
            }

          
            Spacer()
            Button {
                
            } label: {
                VStack{
                    
                    Image(systemName: "atom")
                    
                    Text("Жильё")
                }
            }
            Spacer()
            Button {
                
            } label: {
                VStack{
                    
                    Image(systemName: "atom")
                    
                    Text("События")
                }
            }
            Spacer()
            Button {
                
            } label: {
                VStack{
                    
                    Image(systemName: "atom")
                    
                    Text("Новости")
                }
            }
            Spacer()
        }
        .buttonStyle(.plain)
        .frame(maxWidth: .infinity)
        
    }
}

struct WhatIsViewViewer_Previews: PreviewProvider {
    static var previews: some View {
        WhatIsViewViewer()
    }
}

